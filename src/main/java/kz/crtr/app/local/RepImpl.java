package kz.crtr.app.local;

import kz.crtr.app.entity.gson.GsonDAction;
import kz.crtr.app.gson.GsonDatatableData;
import kz.crtr.app.gson.GsonResult;

import java.util.List;

/**
 * @author a.kussein
 */
public interface RepImpl {
    GsonDatatableData getReportSt(Integer start, Integer count) throws Exception;

    /**
     * @param source;
     * @return list;
     * @author beljerin;
     * @desc метод возвращает список отчетов
     */
    List<GsonDAction> getDActionListByType(Integer source) throws Exception;

    byte[] getReportConfirmationSS1(Integer reportId, String data, String d1, String graph) throws Exception;

    GsonResult createReport(Long id, String data, Boolean async);

    String getReportBody(Long id);
}
