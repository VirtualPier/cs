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
            name: {
                message: '请输入邮箱地址或者手机号',
                validators: {
                    notEmpty: {
                        message: '该项不能为空!'
                    },
                    regexp:{
                        regexp: /(^1\d{10}$)|(^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$)/,
                        message:"请输入手机号或者邮箱地址!"
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码禁止为空！'
                    }
                }
            }
        }
    });
});