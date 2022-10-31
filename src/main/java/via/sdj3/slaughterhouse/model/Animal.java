package via.sdj3.slaughterhouse.model;


import java.sql.Timestamp;


public class Animal
{
  private String date;
  private double weight;
  private long registrationNumber;
  private String origin;

  public Animal(String date, double weight, long registrationNumber,
      String origin)
  {
    this.date = date;
    this.weight = weight;
    this.registrationNumber = registrationNumber;
    this.origin = origin;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public void setWeight(double weight)
  {
    this.weight = weight;
  }

  public void setRegistrationNumber(long registrationNumber)
  {
    this.registrationNumber = registrationNumber;
  }

  public void setOrigin(String origin)
  {
    this.origin = origin;
  }

  public String getDate()
  {
    return date;
  }

  public double getWeight()
  {
    return weight;
  }

  public long getRegistrationNumber()
  {
    return registrationNumber;
  }

  public String getOrigin()
  {
    return origin;
  }
}
