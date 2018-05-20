package kz.crtr.emaket.utils;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;

/**
 * @author Bekzhan
 */
@Getter
@Setter
public class JDocumentParameter {

    private HashMap<String, Object> parametersMap;
    private JRBeanCollectionDataSource beanColDataSource;
}