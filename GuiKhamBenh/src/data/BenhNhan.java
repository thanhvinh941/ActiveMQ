package data;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name="",propOrder={"benhNhanID","soCMND","hoTen","diaChi"})
@XmlRootElement(name ="BenhNhan")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BenhNhan implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String benhNhanID;
	private String soCMND;
	private String hoTen;
	private String diaChi;
	public String getBenhNhanID() {
		return benhNhanID;
	}
	public void setBenhNhanID(String benhNhanID) {
		this.benhNhanID = benhNhanID;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public BenhNhan(String benhNhanID, String soCMND, String hoTen, String diaChi) {
		super();
		this.benhNhanID = benhNhanID;
		this.soCMND = soCMND;
		this.hoTen = hoTen;
		this.diaChi = diaChi;
	}
	public BenhNhan() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BenhNhan [benhNhanID=" + benhNhanID + ", soCMND=" + soCMND + ", hoTen=" + hoTen + ", diaChi=" + diaChi
				+ "]";
	}
	
	
}
