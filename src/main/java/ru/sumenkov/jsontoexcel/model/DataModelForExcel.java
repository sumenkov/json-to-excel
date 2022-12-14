package ru.sumenkov.jsontoexcel.model;

public class DataModelForExcel {
    private String dt;
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

    public DataModelForExcel(DataModelForExcel data) {
        this.dt = data.getDt();
        this.ptpId = data.getPtpId();
        this.ptpName = data.getPtpName();
        this.tarif = data.getTarif();
        this.routeNum = data.getRouteNum();
        this.prType = data.getPrType();
        this.summ = data.getSumm();
        this.cnt = data.getCnt();
        this.qCnt = data.getQCnt();
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
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
