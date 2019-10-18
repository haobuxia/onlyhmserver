<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="/static/swiper/swiper.css">
    
    <div class="container-fluid" id="index-banner">                      
        <!-- 轮播 -->
        <div class="apple-banner">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide slide1">
                      <!-- <h2 class="homepage-headline txt">赋能未来 连接你我</h2> -->
                    </div>
                    <div class="swiper-slide slide2">                 
                      <!-- <h2 class="homepage-headline txt">用科技成就你的梦想</h2> -->
                    </div>
                    <div class="swiper-slide slide3">
                      <!-- <h2 class="homepage-headline txt">一身才华，一触即发</h2> -->
                    </div>
                </div>
                <div class="swiper-button-prev"><span></span></div>
                <div class="swiper-button-next"><span></span></div>
                <ul class="swiper-pagination autoplay"></ul>
            </div>
        </div>        
    </div>

<script src="/static/swiper/swiper.min.js"></script>
<script src="/static/swiper/apple.js?v=${version}"></script>