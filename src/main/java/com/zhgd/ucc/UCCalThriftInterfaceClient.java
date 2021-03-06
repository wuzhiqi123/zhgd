/**
 * Autogenerated by RTP (based on Thrift) Compiler (1.10.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.zhgd.ucc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.AsyncPushMethodCallback;
import org.apache.thrift.async.TAsyncMethodCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cetc28.rtp.async.client.AsyncPushIface;
import com.cetc28.rtp.beans.URL;
import com.cetc28.rtp.core.ContextHelper;
import com.cetc28.rtp.meta_data.AFile;


/**
 * RTP Client Object
 * 
 * Not Thread Safe
 */
public class UCCalThriftInterfaceClient {

  private static Logger LOGGER = LoggerFactory.getLogger(UCCalThriftInterfaceClient.class);

  private UCCalThriftInterface.AsyncClient asyncClient;

  /**
   * save the conditions when this client was created.
   */
  private URL url;

  private String inquiry;

  /**
   * check this client is whether available or not.
   */
  private volatile boolean available = false;

  private UCCalThriftInterface.AsyncIface proxyAsyncIface;

  private AsyncPushIface proxyAsyncPushIface;

  public UCCalThriftInterfaceClient() {
    super();
  }
  
  public void asyncUCCal_ReceiveCmd(final String stringCmd, final String stringAccess, final AsyncMethodCallback<Integer> resultHandler) throws TException {
    final UCCalThriftInterfaceClientFactory.AsyncCall<Integer> asyncCall = UCCalThriftInterfaceClientFactory.getInstance().new AsyncCall<Integer>() {
      @Override
      public void doExecute() throws TException {
        getProxyAsyncIface().UCCal_ReceiveCmd(stringCmd, stringAccess, resultHandler);
      }
    };
    asyncCall.setThreadCache(ContextHelper.getHeaders());
    UCCalThriftInterfaceClientFactory.getInstance().addAsyncCallTask(asyncCall);
  }

  public void asyncUCCal_ReadIDCardInfo(final String stringID, final AsyncMethodCallback<Map<String,String>> resultHandler) throws TException {
    final UCCalThriftInterfaceClientFactory.AsyncCall<Map<String,String>> asyncCall = UCCalThriftInterfaceClientFactory.getInstance().new AsyncCall<Map<String,String>>() {
      @Override
      public void doExecute() throws TException {
        getProxyAsyncIface().UCCal_ReadIDCardInfo(stringID, resultHandler);
      }
    };
    asyncCall.setThreadCache(ContextHelper.getHeaders());
    UCCalThriftInterfaceClientFactory.getInstance().addAsyncCallTask(asyncCall);
  }

  public int UCCal_ReceiveCmd(final String stringCmd, final String stringAccess) throws TException {
    final Object lock = new Object();
    final UCCalThriftInterfaceClientFactory.AsyncCall<Integer> asyncCall = UCCalThriftInterfaceClientFactory.getInstance().new AsyncCall<Integer>() {
      private final UCCalThriftInterfaceClientFactory.AsyncCall<Integer> asyncCall = this;

      @Override
      public void doExecute() throws TException {
        getProxyAsyncIface().UCCal_ReceiveCmd(stringCmd, stringAccess, new AsyncMethodCallback<Integer>() {
          private final UCCalThriftInterfaceClientFactory.AsyncCall<Integer> innerAsyncCall = asyncCall;
          public void onComplete(Integer response) {
            innerAsyncCall.setSuccess(true);
            innerAsyncCall.setResult(response);
            synchronized (lock) {
              lock.notifyAll();
            }
          }

          public void onError(Exception exception) {
            innerAsyncCall.setSuccess(false);
            innerAsyncCall.setException(exception);
            synchronized (lock) {
              lock.notifyAll();
            }
          }
        });
      }
    };
    asyncCall.setExpListener(new UCCalThriftInterfaceClientFactory.ExceptionListener() {
      @Override
      public void onException(Exception exp) {
        synchronized (lock) {
          lock.notifyAll();
        }
      }
    });
    asyncCall.setThreadCache(ContextHelper.getHeaders());
    UCCalThriftInterfaceClientFactory.getInstance().addAsyncCallTask(asyncCall);
    try {
      synchronized (lock) {
        lock.wait();
      }
    } catch (Exception e) {
      // ignore
    }
    if (asyncCall.isSuccess()) {
      return asyncCall.getResult();
    }
    throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT,"UCCal_ReceiveCmd failed: " + asyncCall.getException() == null ? "unknown exception": asyncCall.getException().getMessage());
  }

  public Map<String,String> UCCal_ReadIDCardInfo(final String stringID) throws TException {
    final Object lock = new Object();
    final UCCalThriftInterfaceClientFactory.AsyncCall<Map<String,String>> asyncCall = UCCalThriftInterfaceClientFactory.getInstance().new AsyncCall<Map<String,String>>() {
      private final UCCalThriftInterfaceClientFactory.AsyncCall<Map<String,String>> asyncCall = this;

      @Override
      public void doExecute() throws TException {
        getProxyAsyncIface().UCCal_ReadIDCardInfo(stringID, new AsyncMethodCallback<Map<String,String>>() {
          private final UCCalThriftInterfaceClientFactory.AsyncCall<Map<String,String>> innerAsyncCall = asyncCall;
          public void onComplete(Map<String,String> response) {
            innerAsyncCall.setSuccess(true);
            innerAsyncCall.setResult(response);
            synchronized (lock) {
              lock.notifyAll();
            }
          }

          public void onError(Exception exception) {
            innerAsyncCall.setSuccess(false);
            innerAsyncCall.setException(exception);
            synchronized (lock) {
              lock.notifyAll();
            }
          }
        });
      }
    };
    asyncCall.setExpListener(new UCCalThriftInterfaceClientFactory.ExceptionListener() {
      @Override
      public void onException(Exception exp) {
        synchronized (lock) {
          lock.notifyAll();
        }
      }
    });
    asyncCall.setThreadCache(ContextHelper.getHeaders());
    UCCalThriftInterfaceClientFactory.getInstance().addAsyncCallTask(asyncCall);
    try {
      synchronized (lock) {
        lock.wait();
      }
    } catch (Exception e) {
      // ignore
    }
    if (asyncCall.isSuccess()) {
      return asyncCall.getResult();
    }
    throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT,"UCCal_ReadIDCardInfo failed: " + asyncCall.getException() == null ? "unknown exception": asyncCall.getException().getMessage());
  }

  UCCalThriftInterface.AsyncClient getAsyncClient() {
    return asyncClient;
  }

  void setAsyncClient(UCCalThriftInterface.AsyncClient asyncClient) {
    this.asyncClient = asyncClient;
  }

  public UCCalThriftInterface.AsyncIface getProxyAsyncIface() {
    return proxyAsyncIface;
  }

  public void setProxyAsyncIface(UCCalThriftInterface.AsyncIface proxyAsyncIface) {
    this.proxyAsyncIface = proxyAsyncIface;
  }

  public AsyncPushIface getProxyAsyncPushIface() {
    return proxyAsyncPushIface;
  }

  public void setProxyAsyncPushIface(AsyncPushIface proxyAsyncPushIface) {
    this.proxyAsyncPushIface = proxyAsyncPushIface;
  }

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  String getInquiry() {
    return inquiry;
  }

  void setInquiry(String inquiry) {
    this.inquiry = inquiry;
  }

  void setAvailable(boolean available) {
    this.available = available;
  }

  public boolean isAvailable() {
    return available;
  }

}
