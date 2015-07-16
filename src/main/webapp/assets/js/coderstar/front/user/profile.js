/**
 * Created by ligson on 2015/4/22 0022.
 */
$(function () {

    /* $(document).ready(function () {
     $('#saveBtn').bind('click', function () {
     var params = $('#setting_form').serialize();
     var url = $('#setting_form').attr('action');
     $.ajax({
     url: url,
     dateType: 'json',
     type: 'post',
     data: params,
     success: function (result) {
     if (result.success) {
     BootstrapDialog.alert({title: "提示信息", message: "保存成功!"});
     } else {
     if (result.isExsit) {
     BootstrapDialog.alert({title: "提示信息", message: "手机号码已经存在!"});
     } else {
     BootstrapDialog.alert({title: "提示信息", message: "保存失败!"});
     }
     }

     }, error: function () {
     BootstrapDialog.alert({title: "提示信息", message: "服务器也有生病的时候，您懂得！"});
     }
     })
     })
     });
     */
    $("#setting_form").bootstrapValidator({
        message: '输入格式不正确！',
        feedbackIcons: {
            //valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nickName: {
                validators: {
                    notEmpty: {
                        message: '该项不能为空!'
                    }
                }
            },
            sex: {
                validators: {
                    notEmpty: {
                        message: '该项不能为空!'
                    }
                }
            },
            introduce: {
                validators: {
                    stringLength: {
                        min: 6,
                        max: 128,
                        message: '个人简介长度是6-128位'
                    }
                }
            },
            qq: {
                validators: {
                    regexp: {
                        regexp: /^\d+$/,
                        message: "请输入正确的QQ号!"
                    }
                }
            },
            cellphone: {
                validators: {
                    notEmpty: {
                        message: '手机号禁止为空！'
                    }, regexp: {
                        regexp: /^1\d{10}$/,
                        message: "请输入正确的手机号!"
                    },
                    remote: {
                        type: 'post',
                        url: '/user/checkUnique',
                        message: '手机号已存在！',
                        delay: 1000
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱禁止为空！'
                    }, regexp: {
                        regexp: /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/,
                        message: "请输入正确的邮箱地址!"
                    }
                },
                remote: {
                    type: 'post',
                    url: '/user/checkUnique',
                    message: '邮箱已存在！',
                    delay: 1000
                }
            },
            web: {}
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();
        // Get the form instance
        var $form = $(e.target);
        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');
        // Use Ajax to submit form data
        $.post($form.attr('action'), $form.serialize(), function (result) {
                if (result.success) {
                    BootstrapDialog.alert({title: "提示信息", message: "保存成功!"});
                } else {
                    if (result.isExsit) {
                        BootstrapDialog.alert({title: "提示信息", message: "手机号码已经存在!"});
                    } else {
                        BootstrapDialog.alert({title: "提示信息", message: "保存失败!"});
                    }
                }
            }
            ,
            'json'
        )
        ;
    });

    var uploader = $("#avatar_uploader");
    var domEl = document.getElementById("avatar_uploader");
    uploader.change(function(){
        var files = domEl.files;
        if(files.length>0){
            var photo = files[0];
            var type = photo.type;
            var fileSize = photo.size;
            console.log(fileSize>(1024*1024*1024));
            if(type.indexOf("image")>=0){
                if(fileSize>(1024*1024*1024)){
                    BootstrapDialog.alert({title:"提示：",message:"图片大小不能超过1M"});
                }else{

                    var modifyPhotoForm = new FormData();
                    modifyPhotoForm.append("photo",photo);
                    $.ajax({
                        url:"/user/modifyPhoto",
                        type:'POST',
                        data:modifyPhotoForm,
                        processData : false,
                        contentType : false,
                        success:function(data1){
                            if(data1.success){
                                BootstrapDialog.alert({title:"提示：",message:"替换成功！"});
                                $("#avatar_src").attr("src",data1.url);
                            }else{
                                BootstrapDialog.warning("替换失败！原因:"+data1.msg);
                            }
                        },
                        error:function(data2){
                            BootstrapDialog.warning("替换失败！原因:"+data2);
                            //var result = JSON.stringify(data1);
                        }
                    });
                }
            }else{
                BootstrapDialog.alert({title:"提示：",message:"请选择图片类型"});
            }
        }else{
            BootstrapDialog.alert({title:"提示：",message:"请选择一张图片"});
        }
    });

});