package cn.willow.demo.controller;

import cn.willow.demo.framework.Cgi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @see <a href="http://localhost:8000/sniffer?url=http://127.0.0.1:8000">嗅探web服务</a>
 *
 * 开启ftp服务 sudo -s launchctl load -w /System/Library/LaunchDaemons/ftp.plist
 * 关闭ftp服务 sudo -s launchctl unload -w /System/Library/LaunchDaemons/ftp.plist
 * @see <a href="http://localhost:8000/sniffer?url=ftp%3a%2f%2fWillow%3a%5c%40127.0.0.1">嗅探ftp服务</a>
 *
 * 打开注释代码
 * @see <a href="http://localhost:8000/sniffer?url=file:///Users/.pk">任意文件读取</a>
 *
 * Created by Willow on 5/1/17.
 */
@Controller
@RequestMapping(value = "/")
public class RequestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String defaultService(Cgi cgi) {
        return "default service";
    }

    @RequestMapping(value = "sniffer", method = RequestMethod.GET)
    @ResponseBody
    public String sniffer(Cgi cgi) {
        cgi.getResponse().setHeader("Content-Type", "text/html;charset=utf-8");
        cgi.getResponse().setCharacterEncoding("UTF-8");
        String url = cgi.getRequest().getParameter("url");
        URL u;
        try {
            u = new URL(url);
            InputStream inputStream = u.openStream();
            OutputStream outputStream = cgi.getResponse().getOutputStream();

//            byte[] b = new byte[1024];
//            int temp = 0, len = 0;
//            while ((temp = inputStream.read()) != -1) {
//                b[len] = (byte) temp;
//                len++;
//            }
//            outputStream.write(b, 0, len);
//            inputStream.close();
//            outputStream.close();
            return "success";

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
