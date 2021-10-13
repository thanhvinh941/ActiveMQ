using System;
using System.Messaging;
using BenhNhanObject;
using System.Windows.Forms;
using Apache.NMS;
using Apache.NMS.ActiveMQ;
using Apache.NMS.ActiveMQ.Commands;
using XMLObjcet;

namespace NhanBenh
{
    public partial class FormNhanBenh : Form
    {
        MessageQueue queue = null;
        public FormNhanBenh()
        {
            InitializeComponent();
        }

        private void buttonLuu_Click(object sender, EventArgs e)
        {

            Console.WriteLine("sending message. Enter to exit.");
            //tạo connection factory
            IConnectionFactory factory = new
           ConnectionFactory("tcp://localhost:61616");
            //tạo connection
            IConnection con = factory.CreateConnection("admin", "admin");
            con.Start();//nối tới MOM
            string id = textBoxMaBN.Text;
            string hoTen = textBoxHoTen.Text;
            string soCMND = textBoxCMND.Text;
            string diaChi = textBoxDiaChi.Text;
            BenhNhan bn = new BenhNhan(id , soCMND, hoTen, diaChi);
            ISession session = con.CreateSession(AcknowledgementMode.AutoAcknowledge);
            ActiveMQQueue destination = new ActiveMQQueue("nhanbenh");
            IMessageProducer producer = session.CreateProducer(destination);
            string xml = new XMLObjectConverter<BenhNhan>().object2XML(bn);
            Console.WriteLine(xml.ToLower());
            IMessage msg = new ActiveMQTextMessage(xml);
            producer.Send(msg);
            session.Close();
            con.Close();

            textBoxMaBN.Text = "";
            textBoxHoTen.Text = "";
            textBoxCMND.Text = "";
            textBoxDiaChi.Text = "";
        }
    }
}
