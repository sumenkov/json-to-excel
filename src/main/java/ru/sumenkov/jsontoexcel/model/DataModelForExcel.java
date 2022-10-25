package ru.sumenkov.jsontoexcel.model;

public class DataModelForExcel {
    private String dt1;
    private Integer ptpId;
    private String ptpName;
    private Double tarif;
    private String routeNum;
    private String prType;
    private Double summ;
    private Integer cnt;
    private Integer qCnt;

    public DataModelForExcel(){
    }

    public DataModelForExcel(String dt1, Integer ptpId, String ptpName, Double tarif, String routeNum, String prType,
                             Double summ, Integer cnt, Integer qCnt)
    {
        this.dt1 = dt1;
        this.ptpId = ptpId;
        this.ptpName = ptpName;
        this.tarif = tarif;
        this.routeNum = routeNum;
        this.prType = prType;
        this.summ = summ;
        this.cnt = cnt;
        this.qCnt = qCnt;
    }

    public String getDt1() {
        return dt1;
    }

    public void setDt1(String dt1) {
        this.dt1 = dt1;
    }

    public Integer getPtpId() {
        return ptpId;
    }

    public void setPtpId(Integer ptpId) {
        this.ptpId = ptpId;
    }

    public String getPtpName() {
        return ptpName;
    }

    public void setPtpName(String ptpName) {
        this.ptpName = ptpName;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public String getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum;
    }

    public String getPrType() {
        return prType;
    }

    public void setPrType(String prType) {
        this.prType = prType;
    }

    public Double getSumm() {
        return summ;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Integer getQCnt() {
        return qCnt;
    }

    public void setQCnt(Integer qCnt) {
        this.qCnt = qCnt;
    }
}
