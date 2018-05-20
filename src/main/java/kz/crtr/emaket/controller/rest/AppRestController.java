package kz.crtr.emaket.controller.rest;

import kz.crtr.emaket.entity.tbl.DCategory;
import kz.crtr.emaket.gson.GsonResult;
import kz.crtr.emaket.local.AppImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/app")
@Log
public class AppRestController {

    @Autowired
    AppImpl appService;

    @RequestMapping(value = "/upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(HttpServletRequest request) throws IOException, ServletException {

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile files = multipartHttpServletRequest.getFile("upload");

        try {
            if (files != null) {
                appService.saveUploadedFiles(files);
            } else {
                return new ResponseEntity("Кате ", new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Сәтті жүктеп салынған - " +
                files.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/createT", method = GET)
    public GsonResult beforeCreateApp(@RequestParam(value = "nameT", required = true) String nameT,
                                  @RequestParam(value = "costT", required = true) BigDecimal costT,
                                  @RequestParam(value = "count", required = true) int count,
                                  @RequestParam(value = "category", required = true) int category,
                                  @RequestParam(value = "descT", required = true) String descT) {
        return appService.saveTData(nameT, costT, count, descT, category);
    }

    @RequestMapping(value = "/createCat", method = GET)
    public GsonResult createCat(@RequestParam(value = "nameT", required = true) String nameT) {
        return appService.createCat(nameT);
    }

    @RequestMapping(value = "/getDcategory", method = GET)
    public List<DCategory> getDcategory() {
        return appService.getDcategory();
    }

    @RequestMapping(value = "/getDcategoryById", method = GET)
    public GsonResult getDcategoryById(@RequestParam(value = "dId") int dId) {
        return appService.getDcategoryById(dId);
    }

    @RequestMapping(value = "/getTovarByDcategoryByName", method = GET)
    public GsonResult getTovarByDcategoryByName(@RequestParam(value = "name") String name) {
        return appService.getTovarByDcategoryByName(name);
    }
}