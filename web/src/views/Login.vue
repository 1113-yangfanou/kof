<template>
  <div class="row" style="margin-top: 20px">
    <div class="col-3"></div>
    <div class="col-6">
      <div class="card">
        <div class="card-body">
          <div class="row justify-content-md-center">
            <div class="col-3">
              <form id="form" method="post" action="login">
                <div class="mb-3">
                  <label for="username" class="form-label">用户名</label>
                  <input name="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label">密码</label>
                  <input name="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                </div>
                <div>
                  <a href="register">还没有账号？点我注册</a>
                </div>
<!--                <div class="error-message"><span style="color: red;">${msg}</span></div>-->
                <button type="submit" class="btn btn-primary" >提交</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login"
}
</script>

<style scoped>

</style>