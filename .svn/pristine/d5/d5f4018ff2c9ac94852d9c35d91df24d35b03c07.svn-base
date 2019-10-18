<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
    <div class="section" id="!top">
        <div class="row">
            <div class="col s12">
                <h4>接口测试的头盔IMEI</h4>
            </div>
            <div class="input-field col s12">
                <input type="text" id="imei" value="${helmetImei}" readonly>
            </div>
            <div class="col s12">
                <a href="#!alipay">支付宝接口</a>
                <a href="#!part">零件接口</a>
            </div>
        </div>
    </div>
    <div class="divider"></div>
    <div class="section" id="!alipay">
        <div class="row">
            <div class="col 12">
                <h4>支付宝接口 <a href="#!top">↑</a></h4>
            </div>
        </div>
        <table class="bordered">
            <tr>
                <td style="max-width: 30%;width: 20%;">
                    <span>1 支付</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/alipay/pay" data-type="form">调用</a>
                </td>
                <td style="max-width: 40%;width: 40%;">
                    <div class="input-field col s12">
                    <textarea id="textarea1-pay" class="materialize-textarea" style="height:110px;">
                            "authCode":"条码或二维码的值",
                            "amount":"0.01",
                            "payType":"partSellOrder",
                            "subject":"测试收款",
                            "description":"本次收款的详细内容描述"
                    </textarea>
                    </div>
                </td>
                <td style="max-width: 50%;width: 40%;">
                    <div class="input-field col s12">
                        <span id="textarea2-pay" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>2 退款</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/alipay/refund" data-type="form">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-refund" class="materialize-textarea" style="height:66px;">
                            "outTradeNo":"收款时的订单号",
                            "amount":2.34,
                            "reason":"之前的收款没有打折,退回折扣费"
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-refund" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>3 查询</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/alipay/query" data-type="form">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-query" class="materialize-textarea" style="height:66px;">
                            "outTradeNo":"收款时的订单号"
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-query" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <div class="divider"></div>
    <div class="section" id="!part">
        <div class="row">
            <div class="col 12">
                <h4>零配件接口 <a href="#!top">↑</a></h4>
            </div>
        </div>

        <table class="bordered">
            <tr>
                <td style="max-width: 30%;width: 20%;">
                    <span>1 获取吨级列表</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetTons">调用</a>
                </td>
                <td style="max-width: 40%;width: 40%;">
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetTons" class="materialize-textarea" style="height:66px;">
                            {
                                "brand":"小松"
                            }
                        </textarea>
                    </div>
                </td>
                <td style="max-width: 50%;width: 40%;">
                    <div class="input-field col s12">
                        <span id="textarea2-GetTons" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>2 获取机型列表</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetVclModel">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-GetVclModel" class="materialize-textarea" style="height:66px;">
                        {
                            "tonID":"HnsAAAB36C3XbY6I"
                        }
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetVclModel" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>3 获取一级系统</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetSystemOne">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetSystemOne" class="materialize-textarea" style="height:132px;">
                              {
                                "systemOne":"发动机",
                                "vclModel":"PC200-8",
                                "page_size":100,
                                "page":1
                            }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetSystemOne" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>4 获取二级系统</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetSystemTwo">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetSystemTwo" class="materialize-textarea" style="height:154px;">
                        {
                            "systemOne":"发动机",
                            "systemTwo":"缸体",
                            "vclModel":"PC200-8",
                            "page_size":100,
                            "page":1
                        }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetSystemTwo" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>5 根据机械和系统获取零件信息</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetPartInfoBySys">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetPartInfoBySys" class="materialize-textarea" style="height:110px;">
                            {
                                "vclModel":"PC200-8",
                                "systemoneid":"qrFcDeAsv0a47+Ss8viXAFnY6PA=",
                                "systemtwoid":"GG/bovz62Ua4tms4scO4HVnY6PA="
                            }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetPartInfoBySys" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>6 获取库存组织</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetWareHouseOrg">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetWareHouseOrg" class="materialize-textarea">{}</textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetWareHouseOrg" class="materialize-textarea" readonly
                              style="height:66px;"></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>7 获取仓库信息</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetWareHouse">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetWareHouse" class="materialize-textarea" style="height:66px;">
                            {
                                "warehouseOrgID":"HnsAAAAAA47M567U"
                            }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetWareHouse" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>8 查询替代件</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetReplacePartInfo">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetReplacePartInfo" class="materialize-textarea" style="height:66px;">
                            {
                                "partNo":"01.6215-81-9510"
                                //"partNo":"01.600-311-3520"
                                //"partNo":"01.20Y-53-17410"
                            }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetReplacePartInfo" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>9 查询价格和即时库存信息（可用库存）</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetStockInfo">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetStockInfo" class="materialize-textarea" style="height:88px;">
                            {
                                "partNo":"01.6215-81-9510",
                                "vclModel":"PC200-8"
                            }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetStockInfo" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>10 根据机号获取客户信息</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetCustomerByVclNo">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetCustomerByVclNo" class="materialize-textarea" style="height:66px;">
                            {
                                "vclNo":"DBBF5495",
                                "vclModel":"PC200-8"
                            }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetCustomerByVclNo" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>11 零件添加到购物车</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/AddPartsCart">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-AddPartsCart" class="materialize-textarea" style="height:88px;">
                               {
                                "partNo":"01.6732-21-1831",
                                "vclModel":"PC200-8",
                                "qty":1,
                                "partName":"塞"
                            }
                        </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-AddPartsCart" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>12 购物车信息列表</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetPartsCart">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                        <textarea id="textarea1-GetPartsCart" class="materialize-textarea"
                                  style="height:66px;">{}</textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetPartsCart" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>13 新增采购订单</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/AddPurchaseOrder">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-AddPurchaseOrder" class="materialize-textarea" style="height:430px;">
                        {
                        "storeOrgID":2,
                        "warehouseID":3,
                        "vclNo":"机号12345678",
                        "vclType":"PC200-8",
                        "usingPart":"使用部位",
                        "remark":"我是服务人员,想采购零件xxx",
                        "partsList":[
                                    {
                                        "partNo":"No-Of-part",
                                        "part":"螺栓",
                                        "qty":3
                                    },
                                    {
                                        "partNo":"No-Of-part",
                                        "part":"火花塞",
                                        "qty":2
                                    }
                                ]
                        }
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-AddPurchaseOrder" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>14 新增销售订单</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/AddSaleOrder">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-AddSaleOrder" class="materialize-textarea" style="height:370px;">
                        {
                            "vclModel":"PC200-8",
                            "vclNo":"JH-1234477",
                            "customerID":123,
                            "invoiceRemark":"发票金额要标准",
                            "partList":[
                                        {
                                            "partNo":"123124",
                                            "part":"零件名",
                                            "warehouseID":"orgID-12",
                                            "repalcePartNo":"partNo-13",
                                            "qty":2
                                        }
                                    ]
                        }
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-AddSaleOrder" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>15 新增移动仓储补库单</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/AddYiDongBuKuOrder">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-AddYiDongBuKuOrder" class="materialize-textarea" style="height:400px;">
                        {
                        "storeOrgID":"storeId-1",
                        "warehouseOutID":"houseId-2",
                        "warehouseInID":"houseId-3",
                        "vclType":"PC200-8",
                        "partsList":[
                                    {
                                        "partNo":"1234",
                                        "part":"零件名称1",
                                        "qty":3
                                    },
                                    {
                                        "partNo":"2234",
                                        "part":"零件名称2",
                                        "qty":2
                                    }
                                ]
                        }
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-AddYiDongBuKuOrder" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>16 头盔号绑定手机号</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/BindTelephone">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-BindTelephone" class="materialize-textarea" style="height:88px;">
                        {
                            "helmetNum":"13413241234",
                            "telephone":"13800138000"
                        }
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-BindTelephone" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <span>17 获取品牌信息</span><br>
                    <a href="javascript:void(0)" class="btn" data-service="/ty/data/GetBrand">调用</a>
                </td>
                <td>
                    <div class="input-field col s12">
                    <textarea id="textarea1-GetBrand" class="materialize-textarea" style="height:66px;">
                        {}
                    </textarea>
                    </div>
                </td>
                <td>
                    <div class="input-field col s12">
                        <span id="textarea2-GetBrand" class="materialize-textarea" readonly></span>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<script>
    function invokeService(dataService, dataType) {
        var imei = $("#imei").val();
        if (imei == undefined) {
            showAlert("请选择测试接口使用的头盔IMEI。如无IMEI，请先到二维码页面绑定头盔.");
            return;
        }
        var lastPartIdx = dataService.lastIndexOf("/");
        var serviceLastPart = dataService.substring(lastPartIdx + 1);
        var requestVar = $.trim($("#textarea1-" + serviceLastPart).val());
        var data;
        if ("form" == dataType) {//form格式
            data = JSON.parse("{" + requestVar + "}");
        } else {//json格式
            data = requestVar;
        }

        $("#textarea2-" + serviceLastPart).text("请求中...");

        ajaxHeaderPost(dataService, data, {
            "imei": imei
        }, function (resp) {
            if(resp != null){
                $("#textarea2-" + serviceLastPart).text(JSON.stringify(resp, null, 2));
            }else{
                $("#textarea2-" + serviceLastPart).text("无反馈");
            }
        }, function (XMLHttpRequest, textStatus, errorThrown) {
            $("#textarea2-" + serviceLastPart).text(textStatus + " " + errorThrown);
        });
    }

    $(function () {
//        $('select').material_select();

        $.each($("a[data-service]"), function (idx, alink) {
            $(alink).off("click").click(function () {
                var dataService = $(alink).attr("data-service");
                var dataType = $(alink).attr("data-type");
                invokeService(dataService, dataType);
            });
        });
    });
</script>