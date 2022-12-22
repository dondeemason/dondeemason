
  <div class="container h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-xl-8">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
		  <div class="row g-3 needs-validation" novalidate>
            <div class="col-xl-12">
              <div class="card-body p-md-5 text-black">
                <h5 class="mb-5 text-center text-center text-uppercase">Add Product</h5>
				<hr class="mb-5">
                <div class="row">
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                    	<div class="form-floating mb-3">
	                      <input type="text" id="productName" placeholder="Product Name" class="form-control form-control-lg" required maxlength="120"/>
	                      <label class="form-label required" for="form3Example1m">Product Name</label>
	                    </div>
                    </div>
                  </div>
                  <%--  
                  <div class="col-md-6 mb-4">
                    <div class="form-outline">
                      <input type="text" id="productId" class="form-control form-control-lg" required />
                      <label class="form-label" for="form3Example1n">Product Code</label>
                    </div>
                  </div>
                  --%>
                </div>

                <div class="form-group">
                	<div class="form-floating mb-3">				    
					    <textarea class="form-control" id="productDescription" placeholder="Product Description" rows="3" maxlength="250" required></textarea>
					    <label class="mb-4 required">Product Description</label>
					 </div>
			  	</div>
			  	
			  	<div class="form-floating mb-3"></div>
			  	<div class="form-floating mb-3">
					<input type="url" class="form-control required" placeholder="https://example.com" pattern="https://.*" id="photo" maxlength="250"/>
					<label class="form-label mb-4 required" for="customFile" required>Image URL</label>
				</div>
				<div class="form-floating mb-3 ">
              	<select id="productStatus" class="form-select required" required>
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
	                      <input type="number" id="productPrice" placeholder="Product Price" class="form-control form-control-lg" step="0.01" onKeyPress="if(this.value.length==12) return false;" required/>
	                      <label class="form-label required" for="form3Example1n">Product Price</label>
                      	</div>
                    </div>
                  </div>
				</div>
				
			  <div class="d-grid mb-4">
                <button id="addproduct" class="btn btn-success btn-login text-uppercase fw-bold" type="button">Add product
              	</button>
              </div> 
              <hr class="my-4">
               <div class="d-grid mb-4">
                <button id="backLandingPageBtn" class="btn btn-primary btn-login text-uppercase fw-bold">Dashboard
              	</button>
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
	initAddProduct();
	initbackLandingPage();
});
</script>
      

