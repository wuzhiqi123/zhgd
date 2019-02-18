package com.zhgd.utile;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{type}")
@Component
public class UdpUtils implements Runnable {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<UdpUtils> udpUtils = new CopyOnWriteArraySet<UdpUtils>();
    //存放拍照后的base64码
    private Map<String ,byte []> map = new HashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private final int MAX_LENGTH = 1024;
    private final int PORT = 12346;
    private DatagramSocket datagramSocket;
    public void run(){
        try {
            init();
            while(true){
                try {
                    byte[] buffer = new byte[MAX_LENGTH];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    if(packet != null ){
                        receive(packet);
                    }else{
                        return ;
                    }

                    String receStr = new String(packet.getData(), 0 , packet.getLength());
                    System.out.println("接收数据包" + receStr);
                    byte[] bt = new byte[packet.getLength()];

                    System.arraycopy(packet.getData(), 0, bt, 0, packet.getLength());
                    System.out.println(packet.getAddress().getHostAddress() + "：" + packet.getPort() + "：" + Arrays.toString(bt));
                    packet.setData(bt);
                    sendInfo(receStr);
                    response(packet);

                } catch (Exception e) {
                    e.printStackTrace();
                    //LoggerUtil.error("udp线程出现异常：" + e.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void receive(DatagramPacket packet) throws Exception {
        datagramSocket.receive(packet);
    }

    public void response(DatagramPacket packet) throws Exception {
        datagramSocket.send(packet);
    }
    /**
     * 初始化连接
     */
    public void init(){
        try {
            datagramSocket = new DatagramSocket(PORT);
            System.out.println("udp服务端已经启动！");
        } catch (Exception e) {
            datagramSocket = null;
            System.out.println("udp服务端启动失败！");
            e.printStackTrace();
        }
    }


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session , @PathParam("type")String type) {
        this.session = session;
        udpUtils.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        if("paizhao".equals(type)){
            Demo();
        }else{
            Thread thread = new Thread(new UdpUtils());
            thread.start();
        }

        //log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            //log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        udpUtils.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        //log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        //log.info("来自客户端的消息:" + message);

        //群发消息
        for (UdpUtils item : udpUtils) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        //log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message)  {
        //log.info(message);
        for (UdpUtils item : udpUtils) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        UdpUtils.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        UdpUtils.onlineCount--;
    }


    public  void Demo() {
        try {
            final Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);
            panel.setMirrored(true);

            final JFrame window = new JFrame("拍照");
            window.addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosed(WindowEvent e) {
                    webcam.close();
                    window.dispose();
                    onClose();
                }
            });
            //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            final JButton button = new JButton("拍照");
            window.add(panel, BorderLayout.CENTER);
            window.add(button, BorderLayout.SOUTH);
            window.setResizable(true);
            window.setAlwaysOnTop(true);
            //window.setLocationRelativeTo(null);
            window.pack();
            window.setVisible(true);
            window.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    window.dispose();
                    onClose();
                    webcam.close();
                }
            });
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    button.setEnabled(false);
                    byte[] b = WebcamUtils.getImageBytes(webcam, ImageUtils.FORMAT_JPG);
                    map.put("paizhao", b);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            window.setVisible(false);
                            JOptionPane.showMessageDialog(null, "拍照成功");
                            button.setEnabled(true);
                            window.dispose();
                            webcam.close();
                            onClose();
                        }
                    });
                    String asB64 = Base64.getEncoder().encodeToString(b);
                    //System.out.print(asB64);
                    sendInfo(asB64);
                }
            });
        }catch(Exception e){
            sendInfo(e.getMessage());
        }
    }
}
