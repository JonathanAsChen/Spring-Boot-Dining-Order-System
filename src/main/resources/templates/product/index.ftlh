<!DOCTYPE html>
<html>
<#include "../common/header.ftlh">

<body>

<div id="wrapper" class="toggled">
    <#include "../common/nav.ftlh">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-3 column"></div>
                <div class="col-md-6 column">
                    <form role="form" action="/sell/seller/product/index" method="post">
                        <div class="form-group">
                            <label for="productName">Name</label>
                            <input type="text" name="productName" class="form-control" id="productName" value="${(productInfo.productName)!''}"/>
                        </div>

                        <div class="form-group">
                            <label for="productPrice">Price</label>
                            <input type="text" name="productPrice" class="form-control" id="productPrice" value="${(productInfo.productPrice)!''}"/>
                        </div>

                        <div class="form-group">
                            <label for="productStock">Stock</label>
                            <input type="number" name="productStock" class="form-control" id="productStock" value="${(productInfo.productStock)!''}"/>
                        </div>

                        <div class="form-group">
                            <label for="productDescription">Description</label>
                            <textarea name="productDescription" class="form-control" id="productDescription">${(productInfo.productDescription)!''}</textarea>
<#--                            <input type="text" name="productStock" class="form-control" id="productStock" value="${(productInfo.productStock)!''}"/>-->
                        </div>

                        <div class="form-group">
                            <label for="productIcon">Image</label>
                            <img style="max-height: 250px; max-width: 250px" src="${(productInfo.productIcon)!''}" alt="">
                            <input type="text" name="productIcon" class="form-control" id="productIcon" value="${(productInfo.productIcon)!''}"/>
                        </div>

                        <div class="form-group">
                            <label for="categoryType">Category</label>
                            <select name="categoryType" id="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                            selected
                                            </#if>
                                    >
                                        ${category.categoryName}
                                    </option>
                                </#list>
                            </select>
                        </div>

                        <input type="text" name="productId" value="${(productInfo.productId)!''}" hidden>

                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>