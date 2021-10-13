using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BenhNhanObject
{
    [Serializable]
    public class BenhNhan
    {
        public string benhNhanID { get; set; }
        public string soCMND { get; set; }
        public string hoTen { get; set; }
        public string diaChi { get; set; }
        public BenhNhan(){}
        public BenhNhan(string id, string soCMND, string fname, string diaChi)
        {
            this.benhNhanID = id; this.hoTen = fname; this.soCMND = soCMND; this.diaChi = diaChi;
        }
        public override string ToString()
        {
            return benhNhanID + "\t" + soCMND + "\t" + hoTen + "\t" + diaChi;
        }

    }
}
