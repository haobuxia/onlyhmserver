<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <div class="section no-pad-bot">
            <div class="row">
                <nav>
                    <div class="nav-wrapper">
                        <div class="col s12">
                            <a href="javascript:void(0)" class="breadcrumb">车辆</a>
                            <a href="javascript:void(0)" class="breadcrumb">${imei}</a>
                            <a href="javascript:void(0)" class="breadcrumb hide" id="yearlistData" data-key="year" data-subkey="month" data-val=""></a>
                            <a href="javascript:void(0)" class="breadcrumb hide" id="monthlistData" data-key="month" data-subkey="day" data-val=""></a>
                            <a href="javascript:void(0)" class="breadcrumb hide" id="daylistData" data-key="day" data-subkey="hour" data-val=""></a>
                            <a href="javascript:void(0)" class="breadcrumb hide" id="hourlistData" data-key="hour" data-subkey="minute" ></a>

                        </div>
                    </div>
                </nav>
            </div>

            <div class="row">
                <div class="col s12" id="mapContainer" style="min-height:600px;height:auto;">
                </div>
            </div>

    </div>
<script>
    var imeiMapPage={};
    imeiMapPage.clientId='${imei}';
    imeiMapPage.data=${data};
</script>
<script src="/static/js/imeiInMap.js?v=${version}"></script>
