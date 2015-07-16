/**
 * Created by Administrator on 2015/4/11 0011.
 */
$(function () {
    var toolbar = [
        ['Font', 'FontSize'],
        ['TextColor', 'BGColor'],
        ['Bold', 'Italic', 'Strike', 'Underline'],
        ['Subscript', 'Superscript'],
        ['NumberedList', 'BulletedList'],
        ['Outdent', 'Indent'],
        ['Blockquote'],
        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
        ['Image', 'Flash', 'Table'],
        ['Link', 'Unlink', 'Anchor'],
        ['Smiley', 'SpecialChar', 'PageBreak'],
        ['CodeSnippet', 'Preview']
    ];
    var config = {
        extraPlugins: 'codesnippet',
        toolbar: toolbar,
        codeSnippet_theme: 'monokai_sublime',
        filebrowserUploadUrl: '/index/uploadFile',
        filebrowserImageUploadUrl: "/index/uploadImage"
    };

    CKEDITOR.replace('ckeditor01', config);
})
;
