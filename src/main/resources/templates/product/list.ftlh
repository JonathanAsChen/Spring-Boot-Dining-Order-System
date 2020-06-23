<html>
    <#include "../common/header.ftlh">

    <body>

    <div id="wrapper" class="toggled">
<#--        sidebar-->
        <#include "../common/nav.ftlh">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Stock</th>
                                <th>Description</th>
                                <th>Category</th>
                                <th>Create time</th>
                                <th>Update time</th>
                                <th colspan="2">Operation</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productPage.content as productInfo>
                                <tr>
                                    <td>${productInfo.productId}</td>
                                    <td>${productInfo.productName}</td>
                                    <td><img height="100" width="100" src="${productInfo.productIcon}" alt=""></td>
                                    <td>${productInfo.productPrice}</td>
                                    <td>${productInfo.productStock}</td>
                                    <td>${productInfo.productDescription}</td>
                                    <td>${productInfo.categoryType}</td>
                                    <td>${productInfo.createTime}</td>
                                    <td>${productInfo.updateTime}</td>
                                    <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">Edit</a></td>
                                    <td>
                                        <#if productInfo.getProductStatusEnum().getMessage() == "on shelf">
                                            <a href="/sell/seller/product/unshelve/${productInfo.getProductId()}">Unshelve</a>
                                        <#else>
                                            <a href="/sell/seller/product/shelve/${productInfo.getProductId()}">Shelve</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <#-- page -->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                                <#if currentPage lte 1>
                                    <li class="disabled"><a href="#">Prev</a></li>
                                <#else>
                                    <li><a href="/sell/seller/product/list?page=${currentPage - 1}&size=${pageSize}">Prev</a></li>
                                </#if>

                                <#assign leftDot = false, rightDot = false>
                                <#list 1..productPage.getTotalPages() as index>

                                    <#if currentPage == index>
                                        <li class="disabled " style="fill: orange"><a href="/sell/seller/product/list?page=${index}&size=${pageSize}">${index}</a></li>

                                    <#elseif (currentPage - index)?abs lt 3 || index == productPage.getTotalPages() || index == 1>
                                        <li><a href="/sell/seller/product/list?page=${index}&size=${pageSize}">${index}</a></li>
                                    <#elseif index lte currentPage - 3 && leftDot == false>
                                        <li class="disabled"><a>...</a></li>
                                        <#assign leftDot = true>
                                    <#elseif index gte currentPage + 3 && rightDot == false>
                                        <li class="disabled"><a>...</a></li>
                                        <#assign rightDot = true>

                                    </#if>



                                </#list>

                                <#if currentPage gte productPage.getTotalPages()>
                                    <li class="disabled"><a href="#">Next</a></li>
                                <#else>
                                    <li><a href="/sell/seller/product/list?page=${currentPage + 1}&size=${pageSize}">Next</a></li>
                                </#if>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>



    </body>
</html>

