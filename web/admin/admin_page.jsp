<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>       
        <div class="container" style="text-align: center; align-content: center;">
            <h2> Add New Manager </h2>
            <form action="AddManager" method="post" style="width: 50%;margin-left: 24%;">
              <div class="form-group">
                  <label for="name">Manager Name:</label>
                  <input type="text" class="form-control text-center" id="name" maxlength="100" placeholder="Enter Manager name" name="name" required >
                </div>
              <div class="form-group">
                <label for="email">Manager Email:</label>
                <input type="email" class="form-control text-center" id="email" maxlength="70" placeholder="Enter Manager email" name="email" required>
              </div>
              <div class="form-group">
                 <label for="mobile">Manager Mobile:</label>
                 <input type="text" class="form-control text-center" id="mobile" placeholder="Enter Manager mobile" maxlength="10" minlength="10" name="mobile" required>
              </div>
                <label>Gender:&nbsp;&nbsp;</label>
                <label class="radio-inline">
                    <input type="radio" name="gender" value="Male" checked>Male
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" value="Female">Female
                </label><br><br>
              <div class="form-group">
                 <label for="m_address">Manager Address:</label>
                 <textarea class="form-control" rows="6" id="m_address" name="manager_address" required></textarea>
              </div> 
              <div class="form-group">
                 <label for="ifsc">IFSC Code: </label>
                 <input type="text" class="form-control text-center" id="ifsc" placeholder="Enter Bank IFSC Code" maxlength="10" minlength="10" name="ifsc" required>
              </div> 
              <div class="form-group">
                 <label for="branch">Branch:</label>
                 <input type="text" class="form-control text-center" id="branch" placeholder="Enter Branch Name" name="branch" required>
              </div>
              <div class="form-group">
                 <label for="b_address">Branch Address:</label>
                 <textarea class="form-control" rows="6" id="b_address" name="address" required></textarea>
              </div>
             <input type="submit" class="btn bg-primary" name="submit" value="ADD MANAGER"/>
             <input type="reset" class="btn bg-primary" value="RESET FORM"/>
            </form>
        </div>
    </body>
</html>
