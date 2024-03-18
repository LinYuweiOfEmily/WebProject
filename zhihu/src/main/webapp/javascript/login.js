new Vue({
    el: '#login',
    data: {
        signInForm: {
            id:'',
            username: '',
            password: ''
        },
        signUpForm: {
            id:'',
            username: '',
            password: '',
            confirmPassword: ''
        },
        index:0,
        showUsernameMsg: false, // 控制信息显示
        usernameMessage: '请输入用户名', // 要显示的信息
        showPasswordMsg: false,
        passwordMessage: '',
        showConfirmMsg: false,
        confirmMessage: ''
    },
    computed: {
        buttonBackground() {
            return this.index === 0 ? 'linear-gradient(to right,#4481eb,#04befe,#4481eb)' : 'linear-gradient(to right,#43e97b,#38f9d7,#43e97b)';
        },
        buttonBoxShadow() {
            return this.index === 0 ? 'none' : '0px 0px 20px rgba(239, 236, 15, 0.6)';
        },
        ballLeft() {
            return this.index === 0 ? '0%' : '61%';
        },
        ballBackgroundColor() {
            return this.index === 0 ? 'rgba(255, 192, 203, 0.8)' : 'rgba(239, 236, 15, 0.8)';
        },
        bottomTransform() {
            return this.index === 0 ? 'rotateY(0deg)' : 'rotateY(180deg)';
        }
    },
    methods: {
        signIn() {
            // 处理登录逻辑
            var _this = this;
            axios({
                method: "post",
                url:"http://localhost:8080/zhihu/user/login",
                data:{
                    username: _this.signInForm.username, // 假设你已经有了用户名
                    password: _this.signInForm.password, // 假设你已经有了密码
                }
            }).then(function (resp){
                if(resp.data=="success"){
                    window.location.href = '/zhihu/index.html';
                }else{
                    alert("用户名或密码错误");
                }
            })
        },
        usernameMsg() {
            if(!this.signInForm.username){
                this.usernameMessage = "请输入用户名";

            }else if(this.signInForm.username.length>16||this.signInForm.username.length<3){
                this.usernameMessage = "用户名长度应该在3-16之间";
            }else{
                this.usernameMessage = "";
            }
            this.showUsernameMsg = true;
        },
        usernameMsg1() {
            if(!this.signUpForm.username){
                this.usernameMessage = "请输入用户名";

            }else if(this.signUpForm.username.length>16||this.signUpForm.username.length<3){
                this.usernameMessage = "用户名长度应该在3-16之间";
            }else{
                this.usernameMessage = "";
            }
            this.showUsernameMsg = true;
        },
        passwordMsg() {
            if(!this.signInForm.password){
                this.passwordMessage = "请输入密码";
            }else if(this.signInForm.password.length>16||this.signInForm.password.length<3){
                this.passwordMessage = "密码长度应该在3-16之间";
            }else{
                this.passwordMessage = "";
            }
            this.showPasswordMsg = true;
        },
        passwordMsg1() {
            if(!this.signUpForm.password){
                this.passwordMessage = "请输入密码";

            }else if(this.signUpForm.password.length>16||this.signUpForm.password.length<3){
                this.passwordMessage = "密码长度应该在3-16之间";
            }else{
                this.passwordMessage = "";
            }
            this.showPasswordMsg = true;
        },
        signUp() {
            // 处理注册逻辑
            var _this = this;
            axios({
                method: "post",
                url:"http://localhost:8080/zhihu/user/register",
                data:{
                    username: _this.signUpForm.username, // 假设你已经有了用户名
                    password: _this.signUpForm.password, // 假设你已经有了密码
                }
            }).then(function (resp){
                if(resp.data=="success"){
                    _this.index = 0;
                }
            })
        },
        changeTab(tab) {
            if (tab === 'signIn') {
                this.index = 0;
            } else if (tab === 'signUp') {
                this.index = 1;
            }
        },
        toggleTab() {
            this.index = this.index === 0 ? 1 : 0;
            this.usernameMessage = '';
            this.passwordMessage = '';
            this.signInForm.username = '';
            this.signInForm.password = '';
            this.signUpForm.username = '';
            this.signUpForm.password = '';
            this.signUpForm.confirmPassword = '';
        },
        isEqual(){
            if(this.signUpForm.password===this.signUpForm.confirmPassword){
                this.confirmMessage = '';
            }else{
                this.confirmMessage = '两次输入密码不一致';
            }
            this.showConfirmMsg = true;
        }
    }
});