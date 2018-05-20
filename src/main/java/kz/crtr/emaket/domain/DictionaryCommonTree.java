package kz.crtr.emaket.domain;

import lombok.Data;

@Data
public class DictionaryCommonTree {
    private String id;    
    private String parentId;
    private String label;    

    public DictionaryCommonTree(String id, String parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }   
}