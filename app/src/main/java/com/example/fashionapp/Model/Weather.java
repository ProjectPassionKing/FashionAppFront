package com.example.fashionapp.Model;

public class Weather {
    private String maxTmp;
    private String minTmp;
    private String tmp;
    private String sky;
    private String rain;
    private String snow;
    private String rainper;

    public String getRainper() {
        return rainper;
    }

    public void setRainper(String rainper) {
        this.rainper = rainper;
    }

    public String getMaxTmp() {
        return maxTmp;
    }

    public void setMaxTmp(String maxTmp) {
        this.maxTmp = maxTmp;
    }

    public String getMinTmp() {
        return minTmp;
    }

    public void setMinTmp(String minTmp) {
        this.minTmp = minTmp;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSnow() {
        return snow;
    }

    public void setSnow(String snow) {
        this.snow = snow;
    }
}
