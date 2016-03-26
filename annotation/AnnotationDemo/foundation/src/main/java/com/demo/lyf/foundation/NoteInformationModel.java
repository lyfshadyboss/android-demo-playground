package com.demo.lyf.foundation;


public class NoteInformationModel extends BusinessBean {

    @SerializeField(type = SerializeType.Int4, length = 0, index = 0, require = false, serverType = "", format = "1=创建完订单后可修改订单的时长限制（分钟）（6.10）;2=最晚出票时间显示文案（6.11）;3=当成功创建机票订单时其中X产品无库存的情况（6.12）;4=用户下一次预订享受的特权优惠提示文案（服务端拼接下发6.13）")
    public int noteType = 0;

    @SerializeField(type = SerializeType.Dynamic, length = 0, index = 1, require = false, serverType = "", format = "当1时：下发修改订单的条件之一时长限制（例如：15）;当3时：下发无库存的X产品列表（如果多个用英文逗号分隔，填写页下发的X产品的typeID和预订时的TypeID一致（9：快速安检通道，10：贵宾休息室，11：安检通道加休息室）；确认页下发的X产品的ID由服务端自定义，从100开始（100：直连接送机）;当4时：下发欢迎下次再来，下次预订成功将赠送“无忧退票险”一份")
    public String noteContent = "";

    public NoteInformationModel() {
        super();
        super.realServiceCode = "10401002";
    }
}
