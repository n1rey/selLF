;(function (w, d) {
    'use strict'
    var valid = {
      validateEmail: function (value) {
        let reg = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i
        if(!reg.test(value)) {
          $('#userEmail').next().text('이메일 형식으로 입력해주세요.').css('color', 'rgb(255 24 24)')
          $('#userEmail').focus()
          return false
        }
        else {
          $('#userEmail').next().text('')
        }
        return true
      },
      validatePassword : function(value) {
        let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,30}$/
        if(!reg.test(value)) {
          $('#userPassword').next().text('비밀번호는 영문, 숫자, 특수기호를 조합한 8자리 이상이어야 합니다').css('color', 'rgb(255 24 24)')
          $('#userPassword').focus()
          return false
        }
        else {
          $('#userPassword').next().text('')
        }
        return true
      }
    };
    w.valid = valid
}(window, document));