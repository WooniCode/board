// main 안에 function을 추가한 이유는, index.js만의 유효범위를 만들어, 함수명 중복이 되더라도 함수가 덮어쓰이는 일이 없도록 하기 위함.
var main = {
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function() {
           _this.save();
        });
        $('#btn-update').on('click', function() {
            _this.update();
        });
        $('#btn-delete').on('click', function() {
           _this.delete();
        });
    },

    save : function() {
        var data = {
                title   : $('#title').val()
            ,   author  : $('#author').val()
            ,   content : $('#content').val()
        }
        $.ajax({
                type : 'POST'
            ,   url : '/api/v1/posts'
            ,   dataType : 'json'
            ,   contentType : 'application/json; charset=utf-8'
            ,   data : JSON.stringify(data)
        }).done(function() {
            alert('글이 생성되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    update : function() {
        var data = {
            title   : $('#title').val()
            ,   content : $('#content').val()
        }

        var id = $("#id").val();

        $.ajax({
            type : 'PUT'
            ,   url : '/api/v1/posts/' + id
            ,   dataType : 'json'
            ,   contentType : 'application/json; charset=utf-8'
            ,   data : JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function() {
        var id = $("#id").val();

        $.ajax({
            type : 'DELETE'
            ,   url : '/api/v1/posts/' + id
            ,   dataType : 'json'
            ,   contentType : 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();