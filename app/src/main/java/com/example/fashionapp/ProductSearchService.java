package com.example.fashionapp;

import android.os.Build;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchService {

    private String rurl = "http://openapi.11st.co.kr/openapi/OpenApiService.tmall?key=";
    String psize = "10";
    private String otherurl = "&pageSize="+psize+"&apiCode=ProductSearch&sortCd=CP&keyword=";
    private String key = BuildConfig.SEARCH_KEY;
    private String keyword; // 검색할 키워드

    public ProductSearchService(String keyword) {
        this.keyword = keyword;
    }

    public List<Product> search() throws IOException, XmlPullParserException {
        List<Product> list = null;
        URL url = new URL(rurl+key+otherurl+keyword);

        URLConnection urlCon = url.openConnection();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();

        parser.setInput(new InputStreamReader(urlCon.getInputStream(), "EUC-KR"));

        int eventType = parser.getEventType();
        Product p = null;

//        XmlPullParser.TEXT //4

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.END_DOCUMENT: //1
                    break;
                case XmlPullParser.START_DOCUMENT: //0
                    list = new ArrayList<Product>();
                    break;
                case XmlPullParser.END_TAG: //3
                    String tag = parser.getName();
                    if (tag.equals("Product")) {
                        list.add(p);
                        p = null; //초기화
                    }
                case XmlPullParser.START_TAG: //2
                    tag = parser.getName();
                        switch (tag) {
                            case "Product":
                                p = new Product();
                                break;
                            case "ProductCode":
                                if (p!=null)
                                    p.setProductCode(parser.nextText());
                                break;
                            case "ProductName":
                                assert p != null;
                                p.setProductName(parser.nextText());
                                break;
                            case "ProductImage300":
                                if (p!=null)
                                    p.setProductImage(parser.nextText());
                                break;
                            case "ProductPrice":
                                if (p!=null)
                                    p.setProductPrice(parser.nextText());
                                break;
                            case "DetailPageUrl":
                                assert p != null;
                                p.setProductDetailUrl(parser.nextText());
                                break;
                            case "SalePrice":
                                assert p != null;
                                p.setSalePrice(parser.nextText());
                                break;
                            case "ReviewCount":
                                assert p != null;
                                p.setReviewCount(parser.nextText());
                                break;
                            case "BuySatisfy":
                                assert p != null;
                                p.setBuySatisfy(parser.nextText());
                                break;
                            case "Discount":
                                assert p!=null;
                                p.setBenefit(parser.nextText());
                                break;
                        }
                    }

            eventType = parser.next();
        }

        assert p != null;
        return list;
    }
}
