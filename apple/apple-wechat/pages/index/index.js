Page({

  /**
  * 页面的初始数据
  */

  data: {　　//初始化为空
    source: '',
  },
  
  /**
   * 上传图片
   */

  uploadimg: function () {
    var that = this;
    wx.chooseImage({ //从本地相册选择图片或使用相机拍照
      count: 1, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        //console.log(res)
        //前台显示
        that.setData({
          source: res.tempFilePaths
        })

        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        var tempFilePaths = res.tempFilePaths
        wx.uploadFile({
          url: 'http://localhost:8080/upload/wechat',
          filePath: tempFilePaths[0],
          name: 'file',
          success: function (res) {
            //打印
            wx.hideToast();
            console.log(res.data);
            wx.showToast({
              title: res.data,
              icon: 'success',
              duration: 2000
            })
          }
        }),
        wx.showToast({
          title: '正在检测……',
          icon: 'loading',
          duration: 100000
        })
      }
    })

  }});