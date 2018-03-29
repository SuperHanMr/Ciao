package com.example.hanyonghui.ciao.bean.request;

import java.net.FileNameMap;

/**
 * Created by hanyonghui on 2017/7/22.
 */
// 请求接口地址类
public class RequestUrls {

    // 測試接口  http://123.56.204.69:8081/ciao/wiki/getPlantTypelist?populars=1&pageNo=1

    //http://123.56.204.69:8081/ciao/resource/b25516ad5acb4e129aa8d121274f906201.jpg
    //请求头
//    public static final String HTTP= "http://123.56.204.69:8081/ciao/";

    public static final String HTTP = " http:/123.56.204.69/yshj/";

    //http://123.56.204.69:8081/ciao/user/userLogin
    // 登陆接口
    public static final String LODING =HTTP+"user/userLogin";

    //获取验证码
    public static final String GETCODE = HTTP+"user/identifyCode";

    //注册接口
    public static final String REGISTRATION = HTTP+"user/userRegister";


    //获取个人信息
    public static final String USERDATA = HTTP+"user/getUserinfo";

    // 修改用戶信息
    public static final String SETUSERDATA = HTTP+"user/setUserinfo";

    // 忘记密码
    public static final String FORGETPASSWORD = HTTP+"user/modifyKey";

    // 微信登陆
    public static final String WXLOGIN = HTTP+"user/weixinRegister";


    //添加种植箱接口
    public static final String ADDBOX = HTTP+"box/addBox";

    // 请求我的植物库植物类型接口
    public static final String LIBRARYURL = HTTP+"wiki/plantSearchTags";

    //请求植物库分类列表接口
    public static final String LIBRARYLISTURL = HTTP+"wiki/getPlantTypelist";

    // 植物资料卡接口
    public static final String PLANDATADATA = HTTP+"wiki/getPlantConserveExplain";

    // 植物资料卡植物描述
    public static final String PLANTDESCRIPTION = HTTP+"wiki/getPlantTypeInfo";

    // 植物养周期接口
    public static final String PLANTDEVELOP = HTTP+"wiki/findGuideByTypeId";

    //提交修改的植物信息
    public static final String POSTPLANDATA = HTTP+"wiki/updateConserveExplain";

    // 修改养成植物周期
    public static final String ADDGRADENER = HTTP+"wiki/addPlantGuide";


    // 提交植物标签
    public static final String POSTTYPE = HTTP+"wiki/updatePlantTypeInfo";

    // 上传图片接口
    public static final String UPLOADIMAGE = HTTP+"servlet/upload";

    // 添加植物基本信息
    public static final String ADDPLANTDATA =HTTP+"wiki/addPlantType";

    //默认头像URL
    public static final String MOHEAD = "http://imgsrc.baidu.com/forum/w%3D580/sign=5e9cc9173301213fcf334ed464e536f8/5941b519ebc4b74590254ad8c5fc1e178b82155b.jpg";

    public static final String ADDPLANTSID = HTTP+"wiki/addPlantTypeId";

    //获取设备列表

    public static final String EQUIPMENTNAMELIST = HTTP+"device/getDevicelist";

    public static final String DEKETEEQUIPMENTNAME = HTTP+"device/deleteDevice ";

    // 查询单个检测仪
    public static final String ENQUIRIESEQUIPMENT = HTTP+ "device/getDevice";

    // 定时控制泵、阀、灯
    public static final String TIMING  =HTTP+"lamp/auto";

    // 手动浇水
    public static final String LAMP = HTTP+"lamp/manual";

    // 初始化设备时间
    public static final String INITTIME = HTTP+"lamp/sendDataTime";

    // 自动浇水开关接口
    public static final String AUTOMATICSWICTH = HTTP+"lamp/openAuto";

    // 添加检测仪
    public static final String ADDDEVICE = HTTP + "device/addDevice";

    // 查询设备任务
    public static final String SWITCHLIST = HTTP+"lamp/switchList";

    // 社区帖子列表
    public static final String POSTLIST = HTTP+"twitter/twitters";

    // 修改设备名称
    public static final String SETDEKETEEQUIPMENAME = HTTP+"device/updateDevice";

    // 更新设备
    public static final String EQIPMENTUPDATA = HTTP+"device/updateFirmwareVersion";

    //添加帖子
    public static final String ADDPOSTING  = HTTP+"twitter/addTwitter";

    // 个人帖子列表
    public static final String MEPOSTLIST = HTTP+"twitter/userTwitters";

    // 社区帖子详情
    public static final String POSTDETAILS = HTTP+"twitter/twitterInfo";

    //帖子评论
    public static final String ADDCOMMENT = HTTP+"twitter/addComment";

    // 帖子点赞
    public static final String ADDTHUMB = HTTP+"twitter/addPraise";

    //取消点赞
    public static final String DELETETHUMB = HTTP+"twitter/delComment";

    // 收到的评论或者点赞
    public static final String MESSAGE = HTTP+"twitter/commentOrPraise";


    public static final String AIOPENLIGHT = "http://123.56.204.69:8081/ciao/lamp/aiOpenLight";
//    http://123.56.204.69:8081/ciao/lamp/aiOpenLight

    public static final String MANUL = HTTP+"lamp/manual";



}
