<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="ISO-8859-1">
<fmt:setLocale value="en_PH"/>

<div class="row">
	<div class="col-lg-1 col-3"><input id="searchReport" type="button" class="btn btn-primary mb-4" value="Search"></input></div>
	<div class="col-lg-2 col-1"><input type="date" value="${date}" class="DateSelector" id = "dateReport"></div>
</div>

<div class="row my-6">

	<div class="col-6"> <h3 class="fs-4 m-0 text-warning">Order Summary Report</h3></div>
	<div class="col-6 d-flex justify-content-end">
		<input id="orderSummary" type="button" class="btn btn-primary mb-4 disabled me-3" value="Order Report"></input>
		<input id="customerSummary" type="button" class="btn btn-primary mb-4" value="Customer Report"></input>
	</div>

	<div class="col text-center">
	
	<div class="table-responsive bg-light">
		<table id="orderReportTable" class="table table-hover table-bordered table-sm">
			<thead class="table-dark">
			   <tr>
			   	  <th scope="col">Expected Delivery Date</th>
	              <th scope="col">Product ID</th>
	              <th scope="col">Product Name</th>
	              <th scope="col">Payment Status</th>
	              <th scope="col">Quantity</th>
	              <th scope="col">Total Price</th>
            	</tr>
            </thead> 
		    <tbody class="table-light">
	          <c:forEach var="orderSummary" items="${orderSummaryList}">
	          	<tr>
	          	
	          	<fmt:parseDate value="${orderSummary.deliveryDate}" pattern="yyyy-MM-dd" var="deliveryDate"/>
					<td><fmt:formatDate value="${deliveryDate}" dateStyle="medium"/></td>
	          	 <!--  <td><c:out value="${ orderSummary.deliveryDate }"/></td>  -->

	              <td><c:out value="${ orderSummary.productId }"/></td>
	              <td><c:out value="${ orderSummary.productName }"/></td>
	              <td><c:out value="${ orderSummary.paymentStatusDesc }"/></td>
	              <td><c:out value="${ orderSummary.totalQuantity }"/></td>
	              <td><fmt:formatNumber type="currency" value="${ orderSummary.totalPrice }" /></td>
			
	            </tr>
	          </c:forEach>
	         </tbody>
		 </table>
	</div>
	<nav class="mt-2">
		<ul id="show_paginator" class="pagination"></ul>
	</nav>
	<div class="col d-flex justify-content-end">
		<input id="btnExportToCsv" type="button" class="btn btn-primary mb-4" value="Export .csv"></input>
	</div>
		
	 </div>
</div>


<script type="text/javascript">
		let currentpage = ${currentPage};
		let totalpage = ${totalpage};
		
		let dataTable = document.getElementById("orderReportTable");
        let btnExportToCsv = document.getElementById("btnExportToCsv");
        
		$( document ).ready(function(){
			initSearchOrderReport();
			//initOpenCustomerReport();
		});
		
		$('#show_paginator').bootpag({
		    total: totalpage,
		    page: currentpage,
		    maxVisible: 5,
		}).on("page", function(event, num){
		   // $(".page-link").html("Page " + num); // or some ajax content loading...
		 	openViewAllReports($("#dateReport").val(), num, 5, "od.product_id", "ASC");
		    // ... after content load -> change total to 10
		    //$(this).bootpag({total: 10, maxVisible: 10});
		 
		});
		
		$('a').addClass('page-link');
	
		// CSV File
        btnExportToCsv.addEventListener("click", () => {
            const exporter = new TableCSVExporter(dataTable);
            const csvOutput = exporter.convertToCSV();
            const csvBlob = new Blob([csvOutput], { type: "text/csv" });
            const blobUrl = URL.createObjectURL(csvBlob);
            const anchorElement = document.createElement("a");
			
            // Default name of the csv file
            anchorElement.href = blobUrl;
            anchorElement.download = "Order_Report_" + $("#dateReport").val() + ".csv";
            anchorElement.click();

            setTimeout(() => {
                URL.revokeObjectURL(blobUrl);
            }, 500);
        });
		
        class TableCSVExporter {
            constructor (table, includeHeaders = true) {
                this.table = table;
                this.rows = Array.from(table.querySelectorAll("tr"));

                if (!includeHeaders && this.rows[0].querySelectorAll("th").length) {
                    this.rows.shift();
                }
            }

            convertToCSV () {
                const lines = [];
                const numCols = this._findLongestRowLength();

                for (const row of this.rows) {
                    let line = "";

                    for (let i = 0; i < numCols; i++) {
                        if (row.children[i] !== undefined) {
                            line += TableCSVExporter.parseCell(row.children[i]);
                        }

                        line += (i !== (numCols - 1)) ? "," : "";
                    }

                    lines.push(line);
                }

                return lines.join("\n");
            }

            _findLongestRowLength () {
                return this.rows.reduce((l, row) => row.childElementCount > l ? row.childElementCount : l, 0);
            }

            static parseCell (tableCell) {
                let parsedValue = tableCell.textContent;
				
                // Replace all double quotes with two double quotes
                parsedValue = parsedValue.replace(/"/g, `""`);

                // If value contains comma, new-line or double-quote, enclose in double quotes
               	parsedValue = /[",\n]/.test(parsedValue) ? '"' + ${'parsedValue'} + '"' : parsedValue;
				
               	//console.log("Print " + ${'parsedValue'});
            
                return parsedValue;
            }
        }
		
</script>