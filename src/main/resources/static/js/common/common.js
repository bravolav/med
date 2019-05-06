$(function(){
  // **************
  // 导航
  // **************
  $('.weui-tabbar').tab({
      defaultIndex: 24,
      activeClass: 'weui-bar__item_on',
      onToggle: function (index) {
        // if (index > 0) {
        //     $.toast(index)
        // }
         if (index == 0) {
           window.location.href = "regList.html";
         }
        if (index == 1) {
          window.location.href = "payOrder.html";
        }
        if (index == 2) {
          window.location.href = "diagnosis.html";
        }
        if (index == 3) {
          window.location.href = "/user/mine";
        }
      }
  });

})



