package org.cghr.gmon.controller

import com.google.gson.Gson
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by ravitej on 10/4/14.
 */
@Controller
@RequestMapping("/bodyImp")
class BodyImpedance {

    @Autowired
    Sql gSql

    BodyImpedance() {

    }

    BodyImpedance(Sql sql) {
        this.gSql = sql
    }


    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    String getData(@PathVariable("customerId") String customerId) {


        def sql = "SELECT customer.customerId,customer.sex,tabc.* from tabc INNER JOIN customer ON tabc.patnr=customer.patnr where customerid=? ORDER BY 1,2,3".toString()
        Map data = gSql.firstRow(sql, [customerId])
        return new Gson().toJson(data)

    }

}
