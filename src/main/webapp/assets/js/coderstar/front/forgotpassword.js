/**
 * Created by ligson on 2015/4/17 0017.
 */
$(function () {
    $("#login_form").bootstrapValidator({
        message: '输入格式不正确！',
        feedbackIcons: {
            //valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱禁止为空！'
                    }, regexp: {
                        regexp: /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/,
                        message: "请输入正确的邮箱地址!"
                    }
                }
            },
            code: {
                validators: {
                    notEmpty: {
                        message: '验证码禁止为空！'
                    }, remote: {
                        url: "/index/checkCode",
                        type: "POST",
                        delay: 1000,
                        message: "验证码不正确!"
                    }
                }
            }
        }
    });
});