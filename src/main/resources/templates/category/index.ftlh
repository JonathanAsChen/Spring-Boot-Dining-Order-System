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
                    <form role="form" action="/sell/seller/category/index" method="post">
                        <div class="form-group">
                            <label for="categoryName">Name</label>
                            <input type="text" name="categoryName" class="form-control" id="categoryName" value="${(productCategory.categoryName)!''}"/>
                        </div>

                        <div class="form-group">
                            <label for="categoryType">Type Number</label>
                            <input type="number" name="categoryType" class="form-control" id="categoryType" value="${(productCategory.categoryType)!''}"/>
                        </div>


                        <input type="number" name="categoryId" value="${(productCategory.categoryId)!''}" hidden>

                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>