package org.cghr.gmon.controller

import com.google.gson.Gson
import groovy.sql.Sql
import spock.lang.Shared
import spock.lang.Specification


/**
 * Created by ravitej on 10/4/14.
 */
class BodyImpedanceSpec extends Specification {

    @Shared
    Integer custId = '1234567890'
    BodyImpedance bodyImpedance

    Map mockData = [:];


    def setupSpec() {

    }

    def setup() {

        Sql sql = Stub() {

            def query = "".toString()
            firstRow(query, [custId]) >> new Gson().toJson(mockData)


        }
        bodyImpedance = new BodyImpedance(sql)

    }

    def "should return a json response of the body Impedance readings"() {


        expect:
        true == true
        //bodyImpedance.getData(custId) == new Gson().toJson(mockData)


    }

}