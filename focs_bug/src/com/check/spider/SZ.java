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

//���ڷ�Ժ��ȡ�����Ϣ����
public class SZ {

    //������Ժ ��03 
    /**
     */
    static final String URL1 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440300";
    //���ڸ��﷨Ժ ��0304
    /**
     */
    static final String URL2 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440302";
    //�����޺���Ժ ��0303
    /**
     */
    static final String URL3 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440301";
    //������ɽ��Ժ ��0305
    /**
     */
    static final String URL4 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440303";
    //�������﷨Ժ ��0308
    /**
     */
    static final String URL5 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440306";
    //���ڱ�����Ժ ��0306
    /**
     */
    static final String URL6 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440304";
    //�������ڷ�Ժ ��0307
    /**
     */
    static final String URL7 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440305";
    //����ǰ����Ժ  ��0391
    /**
     */
    static final String URL8 = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/JudgeDocument/25?ajlb=&fydm=440307";

    /**
     * <p>Description: ���ݰ���ȷ�����ļҷ�Ժ�İ�������ȥ������������</p>
     * @param str ����
     * @return ���з�Ժ��ַ
     */
    public static String chooseOneUrl(String str) {
        if (str.contains("��0304") || str.contains("�")) {
            return URL2;
        }
        if (str.contains("��0303") || str.contains("����")) {
            return URL3;
        }
        if (str.contains("��0305") || str.contains("����")) {
            return URL4;
        }
        if (str.contains("��0308") || str.contains("����")) {
            return URL5;
        }
        if (str.contains("��0306") || str.contains("�")) {
            return URL6;
        }
        if (str.contains("��0307") || str.contains("������")) {
            return URL7;
        }
        if (str.contains("��0391") || str.contains("��ǰ")) {
            return URL8;
        }
        if (str.contains("��03") || str.contains("����")) {
            return URL1;
        }
        return "";
    }

    /**
     * <p>Description: ��ѯ��������</p>
     * @param dataList ���ż���
     * @param webClient �������
     * @param picSavePath ͼƬ����·��
     * @param picType ͼƬ����
     * @param appcode api�ӿ���ݴ���
     */
    public static void getCaseBook(List<String> dataList, WebClient webClient, String picSavePath, String picType,
            String appcode) {
        //��ȡ�����а�������
        SZ sz = new SZ();
        boolean searchResult = sz.isData(dataList, webClient, picSavePath, picType, appcode);
        if (searchResult) {
            System.out.println("��ѯ����");
            System.out.println("���ڷ�Ժץȡ���");
        } else {
            System.out.println("��ѯ����");
            System.out.println("���ڷ�Ժץȡ�����쳣");
        }
    }

    /**
     * <p>Description: ��ѯ����</p>
     * @param dataList �ͻ���������
     * @param webClient �������
     * @param picSavePath ͼƬ����·��
     * @param picType ͼƬ����
     * @param appcode api�ӿ���ݴ���
     * @return boolean����
     */
    public boolean isData(List<String> dataList, WebClient webClient, String picSavePath, String picType, String appcode) {
        // ��ȡҪ��ȡ��·��
        String url = "http://12368.szcourt.gov.cn/frontend/anjiangongkai/caseOpen";
        for (int i = 0; i < dataList.size(); i++) {
            String pageMessage = getPageMessage(url, webClient, dataList.get(i), picSavePath, picType, appcode);
            if (i < dataList.size() - 1) {
                if (pageMessage == "n") {
                    System.out.println(dataList.get(i) + "����ѯʧ�ܣ��������ϵ����Ա");
                }
                if (pageMessage == "y") {
                    System.out.println(dataList.get(i) + "����ѯ����...ִ�У�" + dataList.get(i + 1) + "��ѯ");
                }
                if (pageMessage.contains("û�ж�Ӧ��")) {
                    System.out.println(dataList.get(i) + "����ѯ���Ϊ0������ִ�У�" + dataList.get(i + 1) + "��ѯ");
                }
            } else {
                if (pageMessage == "n") {
                    System.out.println(dataList.get(i) + "����ѯʧ�ܣ��������ϵ����Ա");
                }
                if (pageMessage == "y") {
                    System.out.println(dataList.get(i) + "����ѯ����...");
                }
                if (pageMessage.contains("û�ж�Ӧ��")) {
                    System.out.println(dataList.get(i) + "����ѯ���Ϊ0������");
                }
            }
        }
        return true;
    }

    // ����ͳһ����
    /**
     * <p>Description: ����һ��������������</p>
     * @return WebClient 
     */
    public WebClient setParams() {
        // ��ȡǰ׼������
        // ���������ģ�ͣ���ʽ��Ŀ��Ҫ����Ϊ��̬�ģ���ֹ���汻����
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // ����web�������ز���
        // 1 ����JS
        webClient.getOptions().setJavaScriptEnabled(true);
        // 2 ����Css���ɱ����Զ���������CSS������Ⱦ
        webClient.getOptions().setCssEnabled(false);
        //���ô���  
        /*
         * ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
         * proxyConfig.setProxyHost(listserver.get(0));
         * proxyConfig.setProxyPort(Integer.parseInt(listserver.get(1)));
         */
        //����ssl��֤  
        webClient.getOptions().setUseInsecureSSL(true);

        // 3 �����ض���
        webClient.getOptions().setRedirectEnabled(true);
        /* webClient.getOptions().setThrowExceptionOnFailingStatusCode(false); */
        // webClient.getOptions().setGeolocationEnabled(true);
        // 4 ����cookie����
        webClient.setCookieManager(new CookieManager());
        // 5 ����ajax����
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        // 6 js���д���ʱ���Ƿ��׳��쳣
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 7 ���ó�ʱ
        webClient.getOptions().setTimeout(500000000);
        // 8 ����jsִ�г�ʱʱ��
        webClient.setJavaScriptTimeout(1000000000);
        // 9 �ȴ�js��Ⱦִ�� waitime�ȴ�ʱ��(ms)
        webClient.waitForBackgroundJavaScript(50000000);
        return webClient;
    }

    // ִ������
    /**
     * <p>Description: ��ȡҳ����Ϣ</p>
     * @param url ��ѯ��ַ
     * @param webClient �������
     * @param searchparam ��ѯ����
     * @param picSavePath ͼƬ����·��
     * @param picType ͼƬ����
     * @param appcode api�ӿ���ݴ���
     * @return String 
     */
    public static String getPageMessage(String url, WebClient webClient, String searchparam, String picSavePath,
            String picType, String appcode) {
        // ��ȡ���ʱ��
        TimeRand timeRand = new TimeRand();
        try {
            // ׼������
            WebRequest webRequest = new WebRequest(new URL(url));
            // ��������ʽ
            webRequest.setHttpMethod(HttpMethod.GET);
            // ����ҳ������
            Thread.sleep(timeRand.getTimeInSecond());
            HtmlPage page = webClient.getPage(webRequest);
            System.out.println("��ҳ�����С�����");
            // �̳߳�˯��֮��Ҫ����Ϊ��̬�ģ���ֹ�����
            Thread.sleep(timeRand.getTimeInThree());
            // ץȡ�����
            HtmlInput input = page.getElementByName("appliers");
            System.out.println("���������ѯ��Ϣ������");
            System.out.println("��ʼ��ѯ" + searchparam + "������");
            // �������ֵ
            input.setValueAttribute(searchparam);
            Thread.sleep(timeRand.getTimeInSecond());
            System.out.println("������ɣ�");
            // ץȡ�����ť
            DomNodeList<DomElement> eles = page.getElementsByTagName("input");
            // �ȴ������������ץȡ���Է���һ
            Thread.sleep(timeRand.getTimeInThree());
            // ����ץȡ��������input��ǩ
            for (int i = 0; i < eles.size(); i++) {
                //List<String> resultModelList = new ArrayList<String>();
                DomElement domElement = eles.get(i);
                if (domElement.getAttribute("value").equals("����")) {
                    HtmlInput click = (HtmlInput) domElement;
                    System.out.println("��ѯ�С�����");
                    Thread.sleep(timeRand.getTimeInSecond());
                    Page pageSecod = click.click();
                    // ͬ������Ϊ��̬������
                    Thread.sleep(timeRand.getTimeInThree());
                    System.out.println("���ڼ���Ƿ���ͼƬ��֤�뵯��������");
                    DomNodeList<DomElement> imgs = ((HtmlPage) pageSecod).getElementsByTagName("img");
                    Thread.sleep(timeRand.getTimeInSecond());
                    HtmlImage img = null;
                    // �ж���֤���Ƿ񵯳�
                    if (imgs.size() == 14) {
                        System.out.println("��⵽��֤�룬����ץȡ��֤�롣����");
                        HtmlPage message = (HtmlPage) pageSecod;
                        img = (HtmlImage) imgs.get(13);
                        // ������֤�봢���·���Լ�����
                        File file = new File(picSavePath);
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdir();
                        }
                        img.saveAs(file);
                        Thread.sleep(timeRand.getTimeInSecond());
                        System.out.println("��֤���ѱ��浽ָ��Ŀ¼��ʶ���С�����");
                        // ����ʶ�𣬲����ʶ����
                        /**************************************************************************************/
                        // ��ȡ��֤������� 7c2edbbb492e895f
                        HtmlInput inputSeco = message.getElementByName("code");
                        System.out.println("�����룺");
                        String str = "";
                        Scanner ins = new Scanner(System.in);
                        str = ins.next();
                       // str = picRecognize.getPicCode(picSavePath, picType, appcode);
                        inputSeco.setValueAttribute(str);
                        System.out.println("��֤��������ɣ�׼����֤������");
                        // ���������ť
                        HtmlInput continu = message.getElementByName("sure");
                        System.out.println("��֤�У��ȴ���������Ӧ������");
                        Page fin = continu.click();
                        // HtmlPage fin =
                        Thread.sleep(timeRand.getTimeInThree());
                        // �ж���֤���Ƿ��ͳɹ�
                        int statusCode = fin.getWebResponse().getStatusCode();
                        // �ٴε��������ť
                        String content = ((HtmlPage) fin).asText();
                        if (statusCode == 200) {
                            if (content.contains("û�ж�Ӧ�İ�����Ϣ")) {
                                return "û�ж�Ӧ�İ�����Ϣ";
                            }
                            if (content.contains("����¼")) {
                                // �ٴε��������ť
                                // ��ѯ�õ���ҳ�����page3
                                Thread.sleep(timeRand.getTimeInThree());
                                URL urlSecod = ((HtmlPage) pageSecod).getUrl();
                                System.out.println("��֤�ɹ������ʵ�ַ��" + urlSecod.toString());
                                // �ж��Ƿ�ֻ��һҳ
                                int start = content.lastIndexOf("(");
                                int end = content.indexOf("����¼");
                                String pagenum = content.substring(start + 1, end);
                                Integer clicknum = Integer.parseInt(pagenum);
                                System.out.println("��ѯ�����" + clicknum + "����¼��");
                                String urlSecoBd = urlSecod.toString();
                                String replace = "";
                                if (clicknum > 50) {
                                    //����ҳ��
                                    int indexflag = clicknum % 50 == 0 ? clicknum / 50 : clicknum / 50 + 1;
                                    System.out.println("���У�" + indexflag + "ҳ����");

                                    //ֻץȡǰ400��
                                    if (indexflag >= 8) {
                                        indexflag = 8;
                                    }

                                    for (int j = 0; j < indexflag; j++) {
                                        System.out.println("��ѯ�ڣ�" + j + 1 + "ҳ��������");
                                        replace = urlSecoBd.replace("?page=&pageLimit=&caseNo", "?page=" + j
                                                + "&pageLimit=" + 50 + "&caseNo");
                                        // ׼������
                                        WebRequest webRequest1 = new WebRequest(new URL(replace));
                                        // ��������ʽ
                                        webRequest1.setHttpMethod(HttpMethod.GET);
                                        // ����ҳ������
                                        Thread.sleep(timeRand.getTimeInThree());
                                        HtmlPage newallpage = webClient.getPage(webRequest1);
                                        // ��������ظ����õĶ���
                                        handlePageResult(newallpage.asXml(), webClient, searchparam);
                                    }
                                } else {
                                    replace = urlSecoBd.replace("?page=&pageLimit=&caseNo", "?page=0&pageLimit="
                                            + clicknum + "&caseNo");
                                    // ׼������
                                    WebRequest webRequest1 = new WebRequest(new URL(replace));
                                    // ��������ʽ
                                    webRequest1.setHttpMethod(HttpMethod.GET);
                                    // ����ҳ������
                                    Thread.sleep(timeRand.getTimeInThree());
                                    HtmlPage newallpage = webClient.getPage(webRequest1);
                                    // ��������ظ����õĶ���
                                    handlePageResult(newallpage.asXml(), webClient, searchparam);
                                }
                            }
                            if (content.contains("��������֤��") && !content.contains("û�ж�Ӧ��")) {
                                System.out.println("��֤ʧ�ܣ�����ִ�����󡣡���");
                                getPageMessage(url, webClient, searchparam, picSavePath, picType, appcode);
                            }
                        } else {
                            System.out.println("��������Ӧ�쳣������status=" + statusCode + "    �������󡣡���");
                            i = i - 1;
                            continue;
                        }
                        /**************************************************************************************/
                    } else {
                        System.out.println("û�м�⵽��֤�룬����ִ������");
                        HtmlPage pagefour = (HtmlPage) pageSecod;
                        Thread.sleep(timeRand.getTimeInThree());
                        System.out.println("�������ӣ�" + pagefour.getUrl().toString() + "������");
                        Thread.sleep(timeRand.getTimeInSecond());
                        String content = pagefour.asText();
                        if (content.contains("û�ж�Ӧ�İ�����Ϣ")) {
                            //boolean handlePageResult = handlePageResult("��ѯ���Ϊ0", webClient, searchparam);
                            return searchparam + "û�ж�Ӧ�İ�����Ϣ";
                        }
                        // �ж��Ƿ�ֻ��һҳ
                        int start = content.lastIndexOf("(");
                        int end = content.indexOf("����¼");
                        String pagenum = content.substring(start + 1, end);
                        Integer clicknum = Integer.parseInt(pagenum);
                        System.out.println("��ѯ�����" + clicknum + "����¼��");
                        // page2.getUrl().toString();
                        String urlSecoLl = pageSecod.getUrl().toString();
                        String replace = "";
                        //List<String> resultModelList = new ArrayList<String>();
                        if (clicknum > 50) {
                            //����ҳ��
                            int indexflag = clicknum % 50 == 0 ? clicknum / 50 : clicknum / 50 + 1;
                            System.out.println("���У�" + indexflag + "ҳ����");
                            //List<String> resultModelList = new ArrayList<String>();
                            //ֻץȡǰ400��
                            if (indexflag >= 5) {
                                indexflag = 8;
                            }

                            Integer indexff = 0;
                            for (int j = 0; j < indexflag; j++) {
                                indexff++;
                                System.out.println("����ִ�е�:" + indexff + "ҳ���󡣡���");
                                replace = urlSecoLl.replace("?page=&pageLimit=&caseNo", "?page=" + j + "&pageLimit="
                                        + 50 + "&caseNo");
                                // ׼������
                                WebRequest webRequest1 = new WebRequest(new URL(replace));
                                // ��������ʽ
                                webRequest1.setHttpMethod(HttpMethod.GET);
                                // ����ҳ������
                                Thread.sleep(timeRand.getTimeInThree());
                                HtmlPage newallpage = webClient.getPage(webRequest1);
                                // ��������ظ����õĶ���
                                handlePageResult(newallpage.asXml(), webClient, searchparam);
                            }
                        } else {
                            System.out.println("��ѯ�������50��");
                            replace = urlSecoLl.replace("?page=&pageLimit=&caseNo", "?page=0&pageLimit=" + clicknum
                                    + "&caseNo");
                            // ׼������
                            WebRequest webRequest1 = new WebRequest(new URL(replace));
                            // ��������ʽ
                            webRequest1.setHttpMethod(HttpMethod.GET);
                            // ����ҳ������
                            Thread.sleep(timeRand.getTimeInThree());
                            HtmlPage newallpage = webClient.getPage(webRequest1);
                            // ��������ظ����õĶ���
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

    //����ÿһҳ��ѯ�Ľ��
    /**
     * <p>Description: ����ûһҳ�Ĳ�ѯ���</p>
     * @param resultString xml����ҳ�����
     * @param webClient �������
     * @param searchCompany ��ѯ����
     * @return boolean����
     */
    public static boolean handlePageResult(String resultString, WebClient webClient, String searchCompany) {
        Map<Integer, Map<String, String>> resultList = new HashMap<Integer, Map<String, String>>();
        List<String> casscode = new ArrayList<String>();
        DataCRUD dataCrud = new DataCRUD();
        TimeRand timeRand = new TimeRand();
        DataHandle dataHandle = new DataHandle();
        if (resultString == null || "".equals(resultString)) {
            System.out.println("��ҳ�����쳣�����¼��ء�����");
            return false;
        }
        if (resultString.equals("��ѯ���Ϊ0")) {
            System.out.println(resultString);
            return false;
        }
        String pageMessage = resultString;
        // �����ص�����ת���ɾ�̬ҳ��
        Document parse = Jsoup.parse(pageMessage);
        // ��ȡ��̬ҳ�����н��������
        Elements elementsByTag = parse.getElementsByTag("tr");
        String mesgorign = "���ڷ�Ժ�������Ϸ���ƽ̨  http://12368.szcourt.gov.cn";
        Integer flagg = 0;
        for (Element ele : elementsByTag) {
            Map<String, String> paramMap = new HashMap<String, String>();
            flagg++;
            try {
                String hreflink = ele.attr("onclick");
                int st = hreflink.indexOf("frontend");
                String getExcelData = "http://12368.szcourt.gov.cn/" + hreflink.substring(st, hreflink.length() - 1);
                // ׼������
                WebRequest webRequest = new WebRequest(new URL(getExcelData));
                // ��������ʽ
                webRequest.setHttpMethod(HttpMethod.GET);
                // ����ҳ������
                Thread.sleep(timeRand.getTimeInSecond());
                HtmlPage page = webClient.getPage(webRequest);
                Document result = Jsoup.parse(page.asXml());
                Elements elementsByTag2 = result.getElementsByTag("tr");
                // ����ÿһ����ѯ�������Ϣ
                for (int j = 1; j < elementsByTag2.size(); j++) {
                    Element element = elementsByTag2.get(j);
                    Elements elementsByTag3 = element.getElementsByTag("td");
                    String paramsf = elementsByTag3.get(0).text().trim();
                    String paramss = elementsByTag3.get(1).text().trim();
                    paramMap = dataHandle.setParams(paramsf, paramss, mesgorign, searchCompany, paramMap);
                    resultList.put(flagg, paramMap);
                    if (elementsByTag3.get(0).text().trim().contains("����")) {
                        casscode.add(elementsByTag3.get(1).text().trim());
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("��Ч���ӡ�����");
            } catch (FailingHttpStatusCodeException e) {
                System.out.println("��Ч���ӡ�����");
            } catch (IOException e) {
                System.out.println("��Ч���ӡ�����");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("��Ч���ӡ�����");
            }

        }

        /**����������  start*/
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Integer keyff : resultList.keySet()) {
            list.add(resultList.get(keyff));
        }

        //List<Map<String, String>>Ϊ����
        dataCrud.caseAdd(list);
        /**����������  end*/

        System.out.println("��ǰҳ�����Ѵ������ݿ⣡");
        //���ݰ���ȥ���������   casscode list
        boolean caseBookByCaseNum = getCaseBookByCaseNum(casscode, webClient);
        return caseBookByCaseNum;
    }

    //���ݰ���������������
    /**
     * <p>Description: ���ݰ��Ų�ѯ��������</p>
     * @param caseNumList ���ż���
     * @param webClient �������
     * @return boolean 
     */
    public static boolean getCaseBookByCaseNum(List<String> caseNumList, WebClient webClient) {
        Map<String, String> bookcoode = new HashMap<String, String>();
        DataCRUD dataCrud = new DataCRUD();
        TimeRand timeRand = new TimeRand();
        //���ݰ����ж����ĸ���Ժ�İ�������ȥ��ȡ�þ�����
        for (int ppp = 0; ppp < caseNumList.size(); ppp++) {
            //���ô������������˿�
            try {
                String str = caseNumList.get(ppp);
                System.out.println(str);
                //����Ҫ��ȡ��url
                String chooseOneUrl = chooseOneUrl(str);
                //׼������
                WebRequest webRequest = new WebRequest(new URL(chooseOneUrl));
                //��������ʽ
                webRequest.setHttpMethod(HttpMethod.GET);
                //����ҳ������
                Thread.sleep(timeRand.getTimeInSecond());
                HtmlPage page = webClient.getPage(webRequest);
                HtmlInput inputcase = (HtmlInput) page.getElementById("caseNo");
                inputcase.setValueAttribute(str);
                HtmlForm htmlForm = page.getForms().get(0);
                List<HtmlInput> inputsByValue = htmlForm.getInputsByValue("����");
                Page click = inputsByValue.get(0).click();
                HtmlPage resultpage = (HtmlPage) click;
                if (resultpage.asText().contains("������")) {
                    System.out.println("�������鲻���ڣ�ִ����һ����ѯ");
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
                            //׼������
                            WebRequest webRequest1 = new WebRequest(new URL(bookurl));
                            //��������ʽ
                            webRequest1.setHttpMethod(HttpMethod.GET);
                            //����ҳ������
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
                System.out.println("�����ˣ�����������£�����ִ�иôβ�ѯ����");
                e.printStackTrace();
                ppp = ppp - 1;
                continue;
            } catch (IOException e) {
                System.out.println("�����ˣ�����������£�����ִ�иôβ�ѯ����");
                e.printStackTrace();
                ppp = ppp - 1;
                continue;
            }
        }

        //���²����������·��
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
