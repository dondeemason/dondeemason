<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>

<c:set var="productId" scope="session" value="${ product.productId }"/>
<c:set var="productName" scope="session" value="${ product.productName }"/>
<c:set var="productDescription" scope="session" value="${ product.productDescription }"/>
<c:set var="productPicture" scope="session" value="${ product.productPicture }"/>
<c:set var="productStatus" scope="session" value="${ product.productStatus }"/>
<c:set var="price" scope="session" value="${ product.price }"/>


  <div class="container h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-xl-8">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
		  <div class="row g-3 needs-validation" novalidate>
            <div class="col-xl-12">
              <div class="card-body p-md-5 text-black">
              	<h5 class="mb-5 text-uppercase text-center card-title">Edit Product</h5>
              	<hr class="mb-5">
                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
	                    <div class="form-floating mb-3">
	                    	<input type="text" id="productName" class="form-control form-control-lg" value="${ productName }" disabled required/>
	                      	<label class="form-label" for="form3Example1m">Product Name</label>
	                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
	                    <div class="form-floating mb-3">
	                      <input type="text" id="productId" class="form-control form-control-lg" value="${ productId }" required disabled />
	                      <label class="form-label" for="form3Example1n">Product Code</label>
	                    </div>
                    </div>
                  </div>
                </div>

                <div class="form-group">
	                <div class="form-floating mb-3">				    
					    <textarea class="form-control" maxlength="250" id="productDescription" rows="3" placeholder="Product Description" required>${ productDescription }</textarea>
					    <label class="mb-4 required">Product Description</label>
				  	</div>
			  	</div>
			  	
			  	<div class="form-floating mb-3">
					<input type="url" maxlength="250" placeholder="Image URL" class="form-control" id="productPicture" value="${ productPicture }" required/>
					<label class="required form-label mb-4 required">Image URL</label>
				</div>
				
				
				<div class="form-floating mb-3 ">
	              	<select class="form-select" id="productStatus" required>
	              	  <c:if test="${ productStatus == 0}">
	              	  	<option selected value="${ productStatus }">Available</option>
	              	  </c:if>
	              	  <c:if test="${ productStatus == 1}">
	              	  	<option selected value="${ productStatus }">Disabled</option>
	              	  </c:if>
	              	  <c:if test="${ productStatus == 2}">
	              	  	<option selected value="${ productStatus }">Removed</option>
	              	  </c:if>
					  <option value="0">Available</option>
					  <option value="1">Disabled</option>
					  <option value="2">Removed</option>
					 </select>
					 <label class="mb-4 required">Product Status</label>
			  	</div>
			  
               	<div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                    	<div class="form-floating mb-3">
	                      <input type="number" id="productPrice" placeholder="Product Price" class="form-control form-control-lg" step=".01" value="${ price }" onKeyPress="if(this.value.length==12) return false;" required/>
	                      <label class="form-label required" for="form3Example1n">Product Price</label>
	                     </div>
                    </div>
                  </div>
				</div>
				
			  <div class="d-grid mb-4">
                <button class="btn btn-success btn-login text-uppercase fw-bold" id="editproduct">Edit product</button>
              </div> 
              <hr class="my-4">
               <div class="d-grid mb-4">
                <button class="btn btn-primary btn-login text-uppercase fw-bold" id="backLandingPageBtn">Dashboard</button>
              </div> 
				
			  </div>
            </div>
           </div>
          </div>
          </div>
        </div>
      </div>
<script type="text/javascript">
$( document ).ready(function(){
	initEditProduct();
	initbackLandingPage();
});
</script>