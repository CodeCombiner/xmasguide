package com.alexshcherbyna.communityxmasguide.json

import spray.json.{JsValue, _}






class Protocol extends DefaultJsonProtocol {


  //implicit val countryFormat = jsonFormat4(Country.apply)
  //implicit val countriesFormat = jsonFormat1(Countries.apply)



  override implicit def listFormat[T :JsonFormat] = new RootJsonFormat[List[T]] {
    def write(list: List[T]) = JsArray(list.map(_.toJson).toVector)
    def read(value: JsValue): List[T] = value match {
      case JsArray(elements) => elements.map(_.convertTo[T])(collection.breakOut)
      case x => deserializationError("Expected List as JsArray, but got " + x)
    }
  }

  override implicit def vectorFormat[T :JsonFormat] = new RootJsonFormat[Vector[T]] {
    def write(list: Vector[T]) = JsArray(list.map(_.toJson))
    def read(value: JsValue): Vector[T] = value match {
      case JsArray(elements) => elements.map(_.convertTo[T])(collection.breakOut)
      case x => deserializationError("Expected Vector as JsArray, but got " + x)
    }
  }

/*
  implicit def jsonObjectFormat[A : ClassTag]: RootJsonFormat[A] = new RootJsonFormat[A] {
    val ct = implicitly[ClassTag[A]]
    def write(obj: A): JsValue = JsObject("value" -> JsString(ct.runtimeClass.getSimpleName))
    def read(json: JsValue): A = ct.runtimeClass.newInstance().asInstanceOf[A]
  }*/

  implicit object CountryFormat extends RootJsonFormat[Country] {
    def write(pch: Country) =
      JsObject(
        "name" -> JsString(pch.name),
        "description" -> JsString(pch.description),
        "imageurl" -> JsString(pch.imageurl),
        "id" -> JsNumber(pch.id))


    def read(value: JsValue) = {
      value.asJsObject.getFields("id","name", "description", "imageurl" ) match {
        case Seq(JsNumber(id),JsString(name), JsString(description), JsString(imageurl)) =>
          new Country(id.toLong,name, description, imageurl)
        case _ => throw new DeserializationException("BaseError object expected")
      }
    }


  }

  implicit object CityFormat extends RootJsonFormat[City] {
    def write(pch: City) =
      JsObject(
        "name" -> JsString(pch.name),
        "description" -> JsString(pch.description),
        "imageurl" -> JsString(pch.imageurl),
        "id" -> JsNumber(pch.id),
        "countryid"  -> JsNumber(pch.countryid),
    "countryname" -> JsString(pch.countryname))

    def read(value: JsValue) = {
      value.asJsObject.getFields("id","name", "description", "imageurl", "countryid", "countryname" ) match {
        case Seq(JsNumber(id),JsString(name), JsString(description), JsString(imageurl), JsNumber(countryid), JsString(countryname)) =>
          new City(id.toLong,name, description, imageurl, countryid.toLong, countryname)
        case _ => throw new DeserializationException("BaseError object expected")
      }
    }


  }


}



