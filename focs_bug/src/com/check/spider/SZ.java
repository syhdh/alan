package com.check.spider;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

//深圳法院爬取结果信息处理
public class SZ {

    //深圳中院 粤03 
    /**
     */
    static final String URL1 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440300";
    //深圳福田法院 粤0304
    /**
     */
    static final String URL2 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440302";
    //深圳罗湖法院 粤0303
    /**
     */
    static final String URL3 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440301";
    //深圳南山法院 粤0305
    /**
     */
    static final String URL4 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440303";
    //深圳盐田法院 粤0308
    /**
     */
    static final String URL5 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440306";
    //深圳宝安法院 粤0306
    /**
     */
    static final String URL6 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440304";
    //深圳龙岗法院 粤0307
    /**
     */
    static final String URL7 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440305";
    //深圳前海法院  粤0391
    /**
     */
    static final String URL8 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440307";

    /**
     * <p>Description: 根据案号确定是哪家法院的案件，再去搜索裁判文书</p>
     * @param str 案号
     * @return 裁判法院地址
     */
    public static String chooseOneUrl(String str) {
        if (str.contains("粤0304") || str.contains("深福")) {
            return URL2;
        }
        if (str.contains("粤0303") || str.contains("深罗")) {
            return URL3;
        }
        if (str.contains("粤0305") || str.contains("深南")) {
            return URL4;
        }
        if (str.contains("粤0308") || str.contains("深盐")) {
            return URL5;
        }
        if (str.contains("粤0306") || str.contains("深宝")) {
            return URL6;
        }
        if (str.contains("粤0307") || str.contains("深龙法")) {
            return URL7;
        }
        if (str.contains("粤0391") || str.contains("深前")) {
            return URL8;
        }
        if (str.contains("粤03") || str.contains("深中")) {
            return URL1;
        }
        return "";
    }

    /**
     * <p>Description: 查询裁判文书</p>
     * @param dataList 案号集合
     * @param webClient 爬虫对象
     * @param picSavePath 图片保存路径
     * @param picType 图片类型
     * @param appcode api接口身份代码
     */
    public static void getCaseBook(List<String> dataList, WebClient webClient, String picSavePath, String picType,
            String appcode) {
        //获取到所有案件案号
        SZ sz = new SZ();
        boolean searchResult = sz.isData(dataList, webClient, picSavePath, picType, appcode);
        if (searchResult) {
            System.out.println("查询结束");
            System.out.println("深圳法院抓取完毕");
        } else {
            System.out.println("查询结束");
            System.out.println("深圳法院抓取出现异常");
        }
    }

    /**
     * <p>Description: 查询详情</p>
     * @param dataList 客户参数集合
     * @param webClient 爬虫对象
     * @param picSavePath 图片保存路径
     * @param picType 图片类型
     * @param appcode api接口身份代码
     * @return boolean对象
     */
    public boolean isData(List<String> dataList, WebClient webClient, String picSavePath, String picType, String appcode) {
        // 获取要爬取的路径
        String url = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/caseOpen";
        for (int i = 0; i < dataList.size(); i++) {
            String pageMessage = getPageMessage(url, webClient, dataList.get(i), picSavePath, picType, appcode);
            if (i < dataList.size() - 1) {
                if (pageMessage == "n") {
                    System.out.println(dataList.get(i) + "：查询失败，请检查或联系管理员");
                }
                if (pageMessage == "y") {
                    System.out.println(dataList.get(i) + "：查询结束...执行：" + dataList.get(i + 1) + "查询");
                }
                if (pageMessage.contains("没有对应的")) {
                    System.out.println(dataList.get(i) + "：查询结果为0。。。执行：" + dataList.get(i + 1) + "查询");
                }
            } else {
                if (pageMessage == "n") {
                    System.out.println(dataList.get(i) + "：查询失败，请检查或联系管理员");
                }
                if (pageMessage == "y") {
                    System.out.println(dataList.get(i) + "：查询结束...");
                }
                if (pageMessage.contains("没有对应的")) {
                    System.out.println(dataList.get(i) + "：查询结果为0。。。");
                }
            }
        }
        return true;
    }

    // 设置统一参数
    /**
     * <p>Description: 设置一个浏览器爬虫对象</p>
     * @return WebClient 
     */
    public WebClient setParams() {
        // 爬取前准备工作
        // 设置浏览器模型，正式项目中要设置为动态的，防止爬虫被禁用
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // 设置web对象的相关参数
        // 1 启动JS
        webClient.getOptions().setJavaScriptEnabled(true);
        // 2 禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setCssEnabled(false);
        //设置代理  
        /*
         * ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
         * proxyConfig.setProxyHost(listserver.get(0));
         * proxyConfig.setProxyPort(Integer.parseInt(listserver.get(1)));
         */
        //忽略ssl认证  
        webClient.getOptions().setUseInsecureSSL(true);

        // 3 启动重定向
        webClient.getOptions().setRedirectEnabled(true);
        /* webClient.getOptions().setThrowExceptionOnFailingStatusCode(false); */
        // webClient.getOptions().setGeolocationEnabled(true);
        // 4 启动cookie管理
        webClient.setCookieManager(new CookieManager());
        // 5 启动ajax代理
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        // 6 js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 7 设置超时
        webClient.getOptions().setTimeout(500000000);
        // 8 设置js执行超时时间
        webClient.setJavaScriptTimeout(1000000000);
        // 9 等待js渲染执行 waitime等待时间(ms)
        webClient.waitForBackgroundJavaScript(50000000);
        return webClient;
    }

    // 执行请求
    /**
     * <p>Description: 获取页面信息</p>
     * @param url 查询地址
     * @param webClient 爬虫对象
     * @param searchparam 查询参数
     * @param picSavePath 图片保存路径
     * @param picType 图片类型
     * @param appcode api接口身份代码
     * @return String 
     */
    public static String getPageMessage(String url, WebClient webClient, String searchparam, String picSavePath,
            String picType, String appcode) {
        // 获取随机时间
        TimeRand timeRand = new TimeRand();
        try {
            // 准备链接
            WebRequest webRequest = new WebRequest(new URL(url));
            // 设置请求方式
            webRequest.setHttpMethod(HttpMethod.GET);
            // 发送页面请求
            Thread.sleep(timeRand.getTimeInSecond());
            HtmlPage page = webClient.getPage(webRequest);
            System.out.println("网页加载中。。。");
            // 线程沉睡，之后要设置为动态的，防止被监测
            Thread.sleep(timeRand.getTimeInThree());
            // 抓取输入框
            HtmlInput input = page.getElementByName("appliers");
            System.out.println("正在输入查询信息。。。");
            System.out.println("开始查询" + searchparam + "。。。");
            // 给输入框赋值
            input.setValueAttribute(searchparam);
            Thread.sleep(timeRand.getTimeInSecond());
            System.out.println("输入完成！");
            // 抓取点击按钮
            DomNodeList<DomElement> eles = page.getElementsByTagName("input");
            // 等待爬虫完成所有抓取，以防万一
            Thread.sleep(timeRand.getTimeInThree());
            // 遍历抓取到的所有input标签
            for (int i = 0; i < eles.size(); i++) {
                //List<String> resultModelList = new ArrayList<String>();
                DomElement domElement = eles.get(i);
                if (domElement.getAttribute("value").equals("检索")) {
                    HtmlInput click = (HtmlInput) domElement;
                    System.out.println("查询中。。。");
                    Thread.sleep(timeRand.getTimeInSecond());
                    Page pageSecod = click.click();
                    // 同理，设置为动态的数据
                    Thread.sleep(timeRand.getTimeInThree());
                    System.out.println("正在监测是否有图片验证码弹出。。。");
                    DomNodeList<DomElement> imgs = ((HtmlPage) pageSecod).getElementsByTagName("img");
                    Thread.sleep(timeRand.getTimeInSecond());
                    HtmlImage img = null;
                    // 判断验证码是否弹出
                    if (imgs.size() == 14) {
                        System.out.println("监测到验证码，正在抓取验证码。。。");
                        HtmlPage message = (HtmlPage) pageSecod;
                        img = (HtmlImage) imgs.get(13);
                        // 设置验证码储存的路径以及名称
                        File file = new File(picSavePath);
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdir();
                        }
                        img.saveAs(file);
                        Thread.sleep(timeRand.getTimeInSecond());
                        System.out.println("验证码已保存到指定目录，识别中。。。");
                        // 进行识别，并输出识别结果
                        /**************************************************************************************/
                        // 获取验证码输入框 7c2edbbb492e895f
                        HtmlInput inputSeco = message.getElementByName("code");
                        System.out.println("请输入：");
                        String str = "";
                        Scanner ins = new Scanner(System.in);
                        str = ins.next();
                       // str = picRecognize.getPicCode(picSavePath, picType, appcode);
                        inputSeco.setValueAttribute(str);
                        System.out.println("验证码输入完成，准备验证。。。");
                        // 点击继续按钮
                        HtmlInput continu = message.getElementByName("sure");
                        System.out.println("验证中，等待服务器响应。。。");
                        Page fin = continu.click();
                        // HtmlPage fin =
                        Thread.sleep(timeRand.getTimeInThree());
                        // 判断验证码是否发送成功
                        int statusCode = fin.getWebResponse().getStatusCode();
                        // 再次点击搜索按钮
                        String content = ((HtmlPage) fin).asText();
                        if (statusCode == 200) {
                            if (content.contains("没有对应的案件信息")) {
                                return "没有对应的案件信息";
                            }
                            if (content.contains("条记录")) {
                                // 再次点击搜索按钮
                                // 查询得到的页面就是page3
                                Thread.sleep(timeRand.getTimeInThree());
                                URL urlSecod = ((HtmlPage) pageSecod).getUrl();
                                System.out.println("验证成功！访问地址：" + urlSecod.toString());
                                // 判断是否只有一页
                                int start = content.lastIndexOf("(");
                                int end = content.indexOf("条记录");
                                String pagenum = content.substring(start + 1, end);
                                Integer clicknum = Integer.parseInt(pagenum);
                                System.out.println("查询结果有" + clicknum + "条记录！");
                                String urlSecoBd = urlSecod.toString();
                                String replace = "";
                                if (clicknum > 50) {
                                    //共有页数
                                    int indexflag = clicknum % 50 == 0 ? clicknum / 50 : clicknum / 50 + 1;
                                    System.out.println("共有：" + indexflag + "页！！");

                                    //只抓取前400条
                                    if (indexflag >= 8) {
                                        indexflag = 8;
                                    }

                                    for (int j = 0; j < indexflag; j++) {
                                        System.out.println("查询第：" + j + 1 + "页。。。。");
                                        replace = urlSecoBd.replace("?page=&pageLimit=&caseNo", "?page=" + j
                                                + "&pageLimit=" + 50 + "&caseNo");
                                        // 准备链接
                                        WebRequest webRequest1 = new WebRequest(new URL(replace));
                                        // 设置请求方式
                                        webRequest1.setHttpMethod(HttpMethod.GET);
                                        // 发送页面请求
                                        Thread.sleep(timeRand.getTimeInThree());
                                        HtmlPage newallpage = webClient.getPage(webRequest1);
                                        // 将结果返回给调用的对象
                                        handlePageResult(newallpage.asXml(), webClient, searchparam);
                                    }
                                } else {
                                    replace = urlSecoBd.replace("?page=&pageLimit=&caseNo", "?page=0&pageLimit="
                                            + clicknum + "&caseNo");
                                    // 准备链接
                                    WebRequest webRequest1 = new WebRequest(new URL(replace));
                                    // 设置请求方式
                                    webRequest1.setHttpMethod(HttpMethod.GET);
                                    // 发送页面请求
                                    Thread.sleep(timeRand.getTimeInThree());
                                    HtmlPage newallpage = webClient.getPage(webRequest1);
                                    // 将结果返回给调用的对象
                                    handlePageResult(newallpage.asXml(), webClient, searchparam);
                                }
                            }
                            if (content.contains("请输入验证码") && !content.contains("没有对应的")) {
                                System.out.println("验证失败！重新执行请求。。。");
                                getPageMessage(url, webClient, searchparam, picSavePath, picType, appcode);
                            }
                        } else {
                            System.out.println("服务器响应异常。。。status=" + statusCode + "    重新请求。。。");
                            i = i - 1;
                            continue;
                        }
                        /**************************************************************************************/
                    } else {
                        System.out.println("没有监测到验证码，继续执行请求");
                        HtmlPage pagefour = (HtmlPage) pageSecod;
                        Thread.sleep(timeRand.getTimeInThree());
                        System.out.println("访问链接：" + pagefour.getUrl().toString() + "。。。");
                        Thread.sleep(timeRand.getTimeInSecond());
                        String content = pagefour.asText();
                        if (content.contains("没有对应的案件信息")) {
                            //boolean handlePageResult = handlePageResult("查询结果为0", webClient, searchparam);
                            return searchparam + "没有对应的案件信息";
                        }
                        // 判断是否只有一页
                        int start = content.lastIndexOf("(");
                        int end = content.indexOf("条记录");
                        String pagenum = content.substring(start + 1, end);
                        Integer clicknum = Integer.parseInt(pagenum);
                        System.out.println("查询结果有" + clicknum + "条记录！");
                        // page2.getUrl().toString();
                        String urlSecoLl = pageSecod.getUrl().toString();
                        String replace = "";
                        //List<String> resultModelList = new ArrayList<String>();
                        if (clicknum > 50) {
                            //共有页数
                            int indexflag = clicknum % 50 == 0 ? clicknum / 50 : clicknum / 50 + 1;
                            System.out.println("共有：" + indexflag + "页！！");
                            //List<String> resultModelList = new ArrayList<String>();
                            //只抓取前400条
                            if (indexflag >= 5) {
                                indexflag = 8;
                            }

                            Integer indexff = 0;
                            for (int j = 0; j < indexflag; j++) {
                                indexff++;
                                System.out.println("正在执行第:" + indexff + "页请求。。。");
                                replace = urlSecoLl.replace("?page=&pageLimit=&caseNo", "?page=" + j + "&pageLimit="
                                        + 50 + "&caseNo");
                                // 准备链接
                                WebRequest webRequest1 = new WebRequest(new URL(replace));
                                // 设置请求方式
                                webRequest1.setHttpMethod(HttpMethod.GET);
                                // 发送页面请求
                                Thread.sleep(timeRand.getTimeInThree());
                                HtmlPage newallpage = webClient.getPage(webRequest1);
                                // 将结果返回给调用的对象
                                handlePageResult(newallpage.asXml(), webClient, searchparam);
                            }
                        } else {
                            System.out.println("查询结果不足50条");
                            replace = urlSecoLl.replace("?page=&pageLimit=&caseNo", "?page=0&pageLimit=" + clicknum
                                    + "&caseNo");
                            // 准备链接
                            WebRequest webRequest1 = new WebRequest(new URL(replace));
                            // 设置请求方式
                            webRequest1.setHttpMethod(HttpMethod.GET);
                            // 发送页面请求
                            Thread.sleep(timeRand.getTimeInThree());
                            HtmlPage newallpage = webClient.getPage(webRequest1);
                            // 将结果返回给调用的对象
                            handlePageResult(newallpage.asXml(), webClient, searchparam);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "n";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "n";
        }
        return "y";
    }

    //处理每一页查询的结果
    /**
     * <p>Description: 处理没一页的查询结果</p>
     * @param resultString xml类型页面代码
     * @param webClient 爬虫对象
     * @param searchCompany 查询参数
     * @return boolean对象
     */
    public static boolean handlePageResult(String resultString, WebClient webClient, String searchCompany) {
        Map<Integer, Map<String, String>> resultList = new HashMap<Integer, Map<String, String>>();
        List<String> casscode = new ArrayList<String>();
        DataCRUD dataCrud = new DataCRUD();
        TimeRand timeRand = new TimeRand();
        DataHandle dataHandle = new DataHandle();
        if (resultString == null || "".equals(resultString)) {
            System.out.println("网页出现异常，重新加载。。。");
            return false;
        }
        if (resultString.equals("查询结果为0")) {
            System.out.println(resultString);
            return false;
        }
        String pageMessage = resultString;
        // 将返回的数据转换成静态页面
        Document parse = Jsoup.parse(pageMessage);
        // 获取静态页面所有结果的链接
        Elements elementsByTag = parse.getElementsByTag("tr");
        String mesgorign = "深圳法院网上诉讼服务平台  http://12368.szcourt.gov.cn";
        Integer flagg = 0;
        for (Element ele : elementsByTag) {
            Map<String, String> paramMap = new HashMap<String, String>();
            flagg++;
            try {
                String hreflink = ele.attr("onclick");
                int st = hreflink.indexOf("frontend");
                String getExcelData = "http://12368.szcourt.gov.cn/" + hreflink.substring(st, hreflink.length() - 1);
                // 准备链接
                WebRequest webRequest = new WebRequest(new URL(getExcelData));
                // 设置请求方式
                webRequest.setHttpMethod(HttpMethod.GET);
                // 发送页面请求
                Thread.sleep(timeRand.getTimeInSecond());
                HtmlPage page = webClient.getPage(webRequest);
                Document result = Jsoup.parse(page.asXml());
                Elements elementsByTag2 = result.getElementsByTag("tr");
                // 这是每一条查询结果的信息
                for (int j = 1; j < elementsByTag2.size(); j++) {
                    Element element = elementsByTag2.get(j);
                    Elements elementsByTag3 = element.getElementsByTag("td");
                    String paramsf = elementsByTag3.get(0).text().trim();
                    String paramss = elementsByTag3.get(1).text().trim();
                    paramMap = dataHandle.setParams(paramsf, paramss, mesgorign, searchCompany, paramMap);
                    resultList.put(flagg, paramMap);
                    if (elementsByTag3.get(0).text().trim().contains("案号")) {
                        casscode.add(elementsByTag3.get(1).text().trim());
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("无效链接。。。");
            } catch (FailingHttpStatusCodeException e) {
                System.out.println("无效链接。。。");
            } catch (IOException e) {
                System.out.println("无效链接。。。");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("无效链接。。。");
            }

        }

        /**数据入库操作  start*/
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Integer keyff : resultList.keySet()) {
            list.add(resultList.get(keyff));
        }

        //List<Map<String, String>>为参数
        dataCrud.caseAdd(list);
        /**数据入库操作  end*/

        System.out.println("当前页数据已存入数据库！");
        //根据案号去查裁判文书   casscode list
        boolean caseBookByCaseNum = getCaseBookByCaseNum(casscode, webClient);
        return caseBookByCaseNum;
    }

    //根据案号搜索裁判文书
    /**
     * <p>Description: 根据案号查询裁判文书</p>
     * @param caseNumList 案号集合
     * @param webClient 爬虫对象
     * @return boolean 
     */
    public static boolean getCaseBookByCaseNum(List<String> caseNumList, WebClient webClient) {
        Map<String, String> bookcoode = new HashMap<String, String>();
        DataCRUD dataCrud = new DataCRUD();
        TimeRand timeRand = new TimeRand();
        //根据案号判断是哪个法院的案件，再去爬取裁决文书
        for (int ppp = 0; ppp < caseNumList.size(); ppp++) {
            //设置代理服务器及其端口
            try {
                String str = caseNumList.get(ppp);
                System.out.println(str);
                //返回要爬取的url
                String chooseOneUrl = chooseOneUrl(str);
                //准备链接
                WebRequest webRequest = new WebRequest(new URL(chooseOneUrl));
                //设置请求方式
                webRequest.setHttpMethod(HttpMethod.GET);
                //发送页面请求
                Thread.sleep(timeRand.getTimeInSecond());
                HtmlPage page = webClient.getPage(webRequest);
                HtmlInput inputcase = (HtmlInput) page.getElementById("caseNo");
                inputcase.setValueAttribute(str);
                HtmlForm htmlForm = page.getForms().get(0);
                List<HtmlInput> inputsByValue = htmlForm.getInputsByValue("检索");
                Page click = inputsByValue.get(0).click();
                HtmlPage resultpage = (HtmlPage) click;
                if (resultpage.asText().contains("不存在")) {
                    System.out.println("裁判文书不存在！执行下一条查询");
                    continue;
                }
                Document parse = Jsoup.parse(resultpage.asXml());
                Elements elementsByTag = parse.getElementsByTag("tr");
                for (Element ele : elementsByTag) {
                    Elements elementsByTag2 = ele.getElementsByTag("a");
                    for (Element elea : elementsByTag2) {
                        String attr = elea.attr("href");
                        if (attr.contains("information")) {
                            String bookurl = "http://12368.szcourt.gov.cn" + attr;
                            Thread.sleep(1000);
                            //准备链接
                            WebRequest webRequest1 = new WebRequest(new URL(bookurl));
                            //设置请求方式
                            webRequest1.setHttpMethod(HttpMethod.GET);
                            //发送页面请求
                            Thread.sleep(timeRand.getTimeInSecond());
                            HtmlPage pagef = webClient.getPage(webRequest1);
                            DomNodeList<DomElement> elementsByTagName = pagef.getElementsByTagName("iframe");
                            String attribute = elementsByTagName.get(0).getAttribute("src");
                            String finalbookurl = "http://12368.szcourt.gov.cn" + attribute;
                            bookcoode.put(str, finalbookurl);
                        }
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("出错了，错误代码如下，重新执行该次查询。。");
                e.printStackTrace();
                ppp = ppp - 1;
                continue;
            } catch (IOException e) {
                System.out.println("出错了，错误代码如下，重新执行该次查询。。");
                e.printStackTrace();
                ppp = ppp - 1;
                continue;
            }
        }

        //更新裁判文书绝对路径
        for (String key : bookcoode.keySet()) {
            if (key == null || "".equals(key)) {
                continue;
            }
            String sql = "update check_ent_case set case_book ='" + bookcoode.get(key) + "' where case_Num= '" + key
                    + "'";
            dataCrud.operateCud(sql);
        }
        return true;
    }
}
