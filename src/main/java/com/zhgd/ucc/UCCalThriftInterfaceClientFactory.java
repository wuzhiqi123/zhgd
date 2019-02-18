/**
 * Autogenerated by RTP (based on Thrift) Compiler (1.10.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.zhgd.ucc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.rtp.async.client.AsyncClientManager;
import com.cetc28.rtp.async.client.AsyncClientManagerHelper;
import com.cetc28.rtp.async.client.AsyncPushIface;
import com.cetc28.rtp.beans.URL;
import com.cetc28.rtp.client.CacheManager;
import com.cetc28.rtp.client.LoadBalance;
import com.cetc28.rtp.client.ThriftClientFactory;
import com.cetc28.rtp.client.WeightLoadBalance;
import com.cetc28.rtp.core.ContextHelper;
import com.cetc28.rtp.exception.RtpServiceException;
import com.cetc28.rtp.exception.RtpServiceInstanceException;
import com.cetc28.rtp.jms.beans.Instance;
import com.cetc28.rtp.jms.beans.Service;
import com.cetc28.rtp.meta_data.AFile;

public class UCCalThriftInterfaceClientFactory extends ThriftClientFactory<UCCalThriftInterface.AsyncIface> {

  private static final Logger LOGGER = LoggerFactory.getLogger(UCCalThriftInterfaceClientFactory.class);

  private LoadBalance loadBalance;

  private ExecuteThread executeThread;

  private BlockingQueue<AsyncCall<?>> queue = new LinkedBlockingQueue<AsyncCall<?>>(100000);

  private volatile boolean running = true;

  /**
   * timeout for sync client.
   */
  private int timeout = 0;

  private static class UCCalThriftInterfaceClientFactoryHolder {
    static final UCCalThriftInterfaceClientFactory instance = init();
    private static UCCalThriftInterfaceClientFactory init() {
      UCCalThriftInterfaceClientFactory temp = new UCCalThriftInterfaceClientFactory();
      temp.init();
      return temp;
    }
  }

  public static UCCalThriftInterfaceClientFactory getInstance() {
    return UCCalThriftInterfaceClientFactoryHolder.instance;
  }

  private void init() {
    // TODO select policy using configuration file
    if (loadBalance == null) {
      loadBalance = new WeightLoadBalance();
    }

    executeThread = new ExecuteThread();
    executeThread.setName("UCCalThriftInterfaceClient_Async_Thread");
    executeThread.setDaemon(true);
    executeThread.start();
  }
  public UCCalThriftInterfaceClient createUCCalThriftInterfaceClient() throws Exception {
    return createUCCalThriftInterfaceClientWithCondition(null);
  }

  public UCCalThriftInterfaceClient createUCCalThriftInterfaceClientWithCondition(String inquiry) throws Exception {
    return updateUCCalThriftInterfaceClient(inquiry, null);
  }

  @Deprecated
  public UCCalThriftInterfaceClient createUCCalThriftInterfaceClient(String inquiry) throws Exception {
    return updateUCCalThriftInterfaceClient(inquiry, null);
  }

  UCCalThriftInterfaceClient updateUCCalThriftInterfaceClient(String inquiry, UCCalThriftInterfaceClient client) throws Exception {
    Service service;
    if (inquiry == null) {
      service = CacheManager.getInstance().queryService(UCCalThriftInterface.SERVICE_NAME);
    } else {
      service = CacheManager.getInstance().queryService(UCCalThriftInterface.SERVICE_NAME, inquiry);
    }
    if (service != null) {
      Instance instance = loadBalance.select(service.getInstances());
      if (instance != null) {
        LOGGER.warn("selected instance is " + instance.toString());
        if (client == null) {
          client = new UCCalThriftInterfaceClient();
        }
        String address = instance.getAddress();
        createAsyncUCCalThriftInterfaceClientProxy(client, inquiry, new URL(address));
        client.setInquiry(inquiry);
        client.setAvailable(true);
        return client;
      } else {
        throw new RtpServiceInstanceException("No Available Service Instance Found Exception!");
      }
    } else {
      throw new RtpServiceException("Service Not Found Exception!");
    }
  }

  /** assign target service ip and port (using tcp protocol) to create client. */
  public UCCalThriftInterfaceClient createUCCalThriftInterfaceClient(String host, int port) throws Exception {
    return createUCCalThriftInterfaceClient(new URL(host + ":" + port));
  }

  public UCCalThriftInterfaceClient createUCCalThriftInterfaceClient(URL address) throws Exception {
    return updateUCCalThriftInterfaceClient(address, null);
  }

  UCCalThriftInterfaceClient updateUCCalThriftInterfaceClient(URL address, UCCalThriftInterfaceClient client) throws Exception {
    Service service = CacheManager.getInstance().queryService(UCCalThriftInterface.SERVICE_NAME);
    // register center has this service instance or not
    boolean exist = false;

    if (service != null) {
      String addressStr = address.toUrlString();
      for (Instance instance : service.getInstances()) {
        if (instance.getAddress().equals(addressStr)) {
          exist = true;
        }
      }
    }
    if (!exist) {
      LOGGER.info("service which address is {} doesn't exist in register certer.", address);
    }

    if (client == null) {
      client = new UCCalThriftInterfaceClient();
    }
    createAsyncUCCalThriftInterfaceClientProxy(client, null, address);
    client.setUrl(address);
    client.setAvailable(true);
    return client;
  }

  private void createAsyncUCCalThriftInterfaceClientProxy(final UCCalThriftInterfaceClient client, String inquery, URL address) throws Exception {
    if (null == clientFactory) {
      clientFactory = createAsyncClientFactory(UCCalThriftInterface.AsyncIface.class, UCCalThriftInterface.SERVICE_NAME, address);
    }
    AsyncClientManager<?> clientManager = AsyncClientManagerHelper.getInstance().getAsyncClientManager(address.getProtocol());
    TTransport transport = clientManager.getTransport(address.toUrlString());
    client.setAsyncClient((UCCalThriftInterface.AsyncClient) createAsyncClient(transport));
    UCCalThriftInterface.AsyncIface asyncIface = (UCCalThriftInterface.AsyncIface)createAsyncIfaceProxy(client.getAsyncClient(), UCCalThriftInterface.AsyncIface.class, UCCalThriftInterface.SERVICE_NAME, inquery, address, loadBalance ,running);
    client.setProxyAsyncIface(asyncIface);
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }
  public void setLoadBalance(LoadBalance loadBalance) {
    this.loadBalance = loadBalance;
  }
  public void setMaxRetryCount(int retryCount) {
    this.maxRetries = retryCount;
  }
  void addAsyncCallTask(AsyncCall<?> asyncCall) {
    queue.add(asyncCall);
  }
  class ExecuteThread extends Thread {
    public void run() {
      while (running) {
        try {
          AsyncCall<?> ac = queue.take();
          ac.execute();
        } catch (Throwable t) {
          LOGGER.warn("excute async thread error.", t);
        }
      }
    }
  }

  abstract class AsyncCall<T> {
  private T result;

  private Exception exception;

  private boolean success;

  private ExceptionListener expListener;

  private Map<String,String> threadCache = new HashMap<String,String>();

    public void execute() {
      try {
        ContextHelper.addHeaders(threadCache);
        doExecute();
      } catch (Exception e) {
        LOGGER.error("do execute fail.", e);
        if (null != expListener) {
          expListener.onException(e);
        }
      }
    }

    abstract void doExecute() throws TException;

    public T getResult() {
      return result;
    }

    public void setResult(T result) {
      this.result = result;
    }

    public Exception getException() {
      return exception;
    }

    public void setException(Exception exception) {
      this.exception = exception;
    }

    public boolean isSuccess() {
      return success;
    }

    public void setSuccess(boolean success) {
      this.success = success;
    }

    public void setThreadCache(Map<String, String> threadCache) {
      this.threadCache = threadCache;
    }

    public ExceptionListener getExpListener() {
      return expListener;
    }

    public void setExpListener(ExceptionListener expListener) {
      this.expListener = expListener;
    }

  }

  public interface ExceptionListener {
    void onException(Exception exp);
  }

}