package example;

import com.HelloWorldServiceLocator;
import com.HelloWorld_PortType;

import java.rmi.activation.Activator;

public class HelloWorldClient {
  public static void main(String[] argv) {
      try {
          HelloWorldServiceLocator locator = new HelloWorldServiceLocator();
          HelloWorld_PortType service = locator.getHelloWorld();

          //Activator service = locator.get();
          // If authorization is required
          //((HelloWorldSoapBindingStub)service).setUsername("user3");
          //((HelloWorldSoapBindingStub)service).setPassword("pass3");
          // invoke business method
          //service.businessMethod();
          String[]form={"1298509345@qq.com","测试邮件","test email"};
          String str = service.sayHelloWorldFrom(form);
          System.out.println(str);

      } catch (javax.xml.rpc.ServiceException ex) {
          ex.printStackTrace();
      } catch (java.rmi.RemoteException ex) {
          ex.printStackTrace();
      }  
  }
}
