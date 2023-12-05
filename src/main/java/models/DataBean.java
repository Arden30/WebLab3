package models;

import db.HibernateManager;
import utils.AreaChecker;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataBean implements Serializable {
    private HibernateManager hibernateManager = new HibernateManager();;
    private List<DataEntity> data;
    private DataEntity dataFromForm = new DataEntity();
    private DataEntity dataFromGraph = new DataEntity();
    private XValue xValue = new XValue();
    private String xValidation;

    public DataBean() {
        data = hibernateManager.getAllFromDB();
        xValue.setAllX();
    }

    public void addFromForm() {
        List<Double> xValues = xValue.setAllX();

        if (xValues.isEmpty()) {
            xValidation = "Choose at least 1 value!";
        } else {
            xValidation = "";
        }

        for (Double x: xValues) {
            DataEntity dataEntity = new DataEntity();
            setTimesAndDate(dataEntity);

            dataEntity.setX(x);
            dataEntity.setR(dataFromForm.getR());
            dataEntity.setY(dataFromForm.getY());

            dataEntity.setResult(AreaChecker.checkHit(x, dataEntity.getY(), dataEntity.getR()));
            if (hibernateManager.addToDB(dataEntity)) {
                data.add(dataEntity);
            }
        }
    }

    public void addFromGraph() {
        DataEntity dataEntity = new DataEntity();
        setTimesAndDate(dataEntity);

        dataEntity.setX(dataFromGraph.getX());
        dataEntity.setR(dataFromGraph.getR());
        dataEntity.setY(dataFromGraph.getY());

        dataEntity.setResult(AreaChecker.checkHit(dataEntity.getX(), dataEntity.getY(), dataEntity.getR()));
        if (hibernateManager.addToDB(dataEntity)) {
            data.add(dataEntity);
        }
    }

    public void setTimesAndDate(DataEntity dataEntity) {
        long startTime = System.nanoTime();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String currentTime = now.format(dateTimeFormatter);
        dataEntity.setCurrentTime(currentTime);
        long runningTime = System.nanoTime() - startTime;
        dataEntity.setRunningTime(runningTime);
    }
    public void clearTable() {
        if (hibernateManager.clear()) {
            data.clear();
        }
    }

    public void validateR(FacesContext facesContext,
                          UIComponent uiComponent, Object o) {
        if ((Double) o < 1 || (Double) o > 4) {
            throw new ValidatorException(new FacesMessage("R must be in [1;4]!"));
        }
    }

    public void validateY(FacesContext facesContext,
                          UIComponent uiComponent, Object o) {
        if ((Double) o <= -3 || (Double) o >= 5) {
            throw new ValidatorException(new FacesMessage("Y must be a number in (-3;5)!"));
        }
    }

    public HibernateManager getDbManager() {
        return hibernateManager;
    }

    public void setDbManager(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public DataEntity getDataFromForm() {
        return dataFromForm;
    }

    public void setDataFromGraph(DataEntity dataFromGraph) {
        this.dataFromGraph = dataFromGraph;
    }

    public DataEntity getDataFromGraph() {
        return dataFromGraph;
    }

    public void setDataFromForm(DataEntity dataFromForm) {
        this.dataFromForm = dataFromForm;
    }

    public XValue getxValue() {
        return xValue;
    }

    public void setxValue(XValue xValue) {
        this.xValue = xValue;
    }

    public String getxValidation() {
        return xValidation;
    }

    public void setxValidation(String xValidation) {
        this.xValidation = xValidation;
    }
}
