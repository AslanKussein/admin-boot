package kz.crtr.emaket.local;

import kz.crtr.emaket.entity.gson.GsonDAction;
import kz.crtr.emaket.gson.GsonDatatableData;
import kz.crtr.emaket.gson.GsonResult;

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
