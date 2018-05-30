package com.lancoo.lgschoolmonitor.playback.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/21 11:28.
 */
public class CameraVideoBean{

    /**
     * error : 0
     * data : [{"FileID":"e4faa65fc867ebc8","FileName":"Teacher(23-31-04).flv",
     * "StartTime":"2018-04-22%2023%3A31%3A04","EndTime":"2018-04-23%2000%3A31%3A04",
     * "FileSize":"1563760732","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F22%E6%97%A5%2F23%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(23-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F22%E6%97%A5%2F23%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(23-31-04).flv"},{"FileID":"3a1922dfb78f2e6e","FileName":"Teacher(00-31-04)
     * .flv","StartTime":"2018-04-23%2000%3A31%3A04","EndTime":"2018-04-23%2001%3A31%3A04",
     * "FileSize":"1574718385","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F00%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(00-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F00%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(00-31-04).flv"},{"FileID":"e2b207bfa312a570","FileName":"Teacher(01-31-04)
     * .flv","StartTime":"2018-04-23%2001%3A31%3A04","EndTime":"2018-04-23%2002%3A31%3A04",
     * "FileSize":"1589145761","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F01%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(01-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F01%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(01-31-04).flv"},{"FileID":"a0edb13def5a7533","FileName":"Teacher(02-31-04)
     * .flv","StartTime":"2018-04-23%2002%3A31%3A04","EndTime":"2018-04-23%2003%3A31%3A04",
     * "FileSize":"1565266675","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F02%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(02-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F02%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(02-31-04).flv"},{"FileID":"33d5a1812cf91949","FileName":"Teacher(03-31-05)
     * .flv","StartTime":"2018-04-23%2003%3A31%3A05","EndTime":"2018-04-23%2004%3A31%3A04",
     * "FileSize":"1585707304","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F03%E6
     * %97%B631%E5%88%8605%E7%A7%92%2FTeacher(03-31-05).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F03%E6%97%B631%E5%88%8605%E7%A7
     * %92%2FTeacher(03-31-05).flv"},{"FileID":"923ebbbf89a3f18c","FileName":"Teacher(04-31-04)
     * .flv","StartTime":"2018-04-23%2004%3A31%3A04","EndTime":"2018-04-23%2005%3A31%3A04",
     * "FileSize":"1574510677","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F04%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(04-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F04%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(04-31-04).flv"},{"FileID":"5237d48702ef5b43","FileName":"Teacher(05-31-04)
     * .flv","StartTime":"2018-04-23%2005%3A31%3A04","EndTime":"2018-04-23%2006%3A31%3A04",
     * "FileSize":"1555875512","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F05%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(05-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F05%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(05-31-04).flv"},{"FileID":"2aa6663eebe1e7af","FileName":"Teacher(06-31-04)
     * .flv","StartTime":"2018-04-23%2006%3A31%3A04","EndTime":"2018-04-23%2007%3A31%3A04",
     * "FileSize":"1553549997","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F06%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(06-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F06%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(06-31-04).flv"},{"FileID":"e38e3572a3b268e1","FileName":"Teacher(07-31-04)
     * .flv","StartTime":"2018-04-23%2007%3A31%3A04","EndTime":"2018-04-23%2008%3A31%3A04",
     * "FileSize":"1540626148","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F07%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(07-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F07%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(07-31-04).flv"},{"FileID":"de817160b27c15eb","FileName":"Teacher(08-31-04)
     * .flv","StartTime":"2018-04-23%2008%3A31%3A04","EndTime":"2018-04-23%2009%3A31%3A04",
     * "FileSize":"1496201920","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F08%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(08-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F08%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(08-31-04).flv"},{"FileID":"f27ee355a1556d25","FileName":"Teacher(09-31-04)
     * .flv","StartTime":"2018-04-23%2009%3A31%3A04","EndTime":"2018-04-23%2010%3A31%3A04",
     * "FileSize":"1499700472","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F09%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(09-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F09%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(09-31-04).flv"},{"FileID":"b2890d8ab925c671","FileName":"Teacher(10-31-04)
     * .flv","StartTime":"2018-04-23%2010%3A31%3A04","EndTime":"2018-04-23%2011%3A31%3A04",
     * "FileSize":"1517700392","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F10%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(10-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F10%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(10-31-04).flv"},{"FileID":"ab66eb0dab3a3eb4","FileName":"Teacher(11-31-04)
     * .flv","StartTime":"2018-04-23%2011%3A31%3A04","EndTime":"2018-04-23%2012%3A31%3A04",
     * "FileSize":"1516673871","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F11%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(11-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F11%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(11-31-04).flv"},{"FileID":"a06d2aa8e99b4fe6","FileName":"Teacher(12-31-04)
     * .flv","StartTime":"2018-04-23%2012%3A31%3A04","EndTime":"2018-04-23%2013%3A31%3A04",
     * "FileSize":"1498027375","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F12%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(12-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F12%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(12-31-04).flv"},{"FileID":"ec9a7e5e4b4f85df","FileName":"Teacher(13-31-04)
     * .flv","StartTime":"2018-04-23%2013%3A31%3A04","EndTime":"2018-04-23%2014%3A31%3A04",
     * "FileSize":"1437904021","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F13%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(13-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F13%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(13-31-04).flv"},{"FileID":"7db37e136b751e0","FileName":"Teacher(14-31-04)
     * .flv","StartTime":"2018-04-23%2014%3A31%3A04","EndTime":"2018-04-23%2015%3A31%3A04",
     * "FileSize":"1438407325","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F14%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(14-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F14%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(14-31-04).flv"},{"FileID":"8b83d030f083a665","FileName":"Teacher(15-31-04)
     * .flv","StartTime":"2018-04-23%2015%3A31%3A04","EndTime":"2018-04-23%2016%3A31%3A04",
     * "FileSize":"1443951702","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F15%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(15-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F15%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(15-31-04).flv"},{"FileID":"2735ec1371780893","FileName":"Teacher(16-31-04)
     * .flv","StartTime":"2018-04-23%2016%3A31%3A04","EndTime":"2018-04-23%2017%3A31%3A04",
     * "FileSize":"1448446821","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F16%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(16-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F16%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(16-31-04).flv"},{"FileID":"e985663c9e03d32e","FileName":"Teacher(17-31-04)
     * .flv","StartTime":"2018-04-23%2017%3A31%3A04","EndTime":"2018-04-23%2018%3A31%3A04",
     * "FileSize":"1475810863","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F17%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(17-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F17%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(17-31-04).flv"},{"FileID":"2cd4efab363c6178","FileName":"Teacher(18-31-04)
     * .flv","StartTime":"2018-04-23%2018%3A31%3A04","EndTime":"2018-04-23%2019%3A31%3A04",
     * "FileSize":"1444917401","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F18%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(18-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F18%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(18-31-04).flv"},{"FileID":"be0d18493b817240","FileName":"Teacher(19-31-04)
     * .flv","StartTime":"2018-04-23%2019%3A31%3A04","EndTime":"2018-04-23%2020%3A31%3A04",
     * "FileSize":"1467531304","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F19%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(19-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F19%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(19-31-04).flv"},{"FileID":"874765affa3e4ccc","FileName":"Teacher(20-31-04)
     * .flv","StartTime":"2018-04-23%2020%3A31%3A04","EndTime":"2018-04-23%2021%3A31%3A04",
     * "FileSize":"1441913553","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F20%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(20-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F20%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(20-31-04).flv"},{"FileID":"16aa6ffa05dc296b","FileName":"Teacher(21-31-04)
     * .flv","StartTime":"2018-04-23%2021%3A31%3A04","EndTime":"2018-04-23%2022%3A31%3A04",
     * "FileSize":"1398951674","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F21%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(21-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F21%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(21-31-04).flv"},{"FileID":"dfe9c4821f4885d7","FileName":"Teacher(22-31-04)
     * .flv","StartTime":"2018-04-23%2022%3A31%3A04","EndTime":"2018-04-23%2023%3A31%3A04",
     * "FileSize":"1467074801","FtpUrl":"ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7
     * %9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F22%E6
     * %97%B631%E5%88%8604%E7%A7%92%2FTeacher(22-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!",
     * "HttpUrl":"http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84
     * %E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F23%E6%97%A5%2F22%E6%97%B631%E5%88%8604%E7%A7
     * %92%2FTeacher(22-31-04).flv"}]
     */

    private String error;
    private List<VideoBean> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<VideoBean> getData() {
        return data;
    }

    public void setData(List<VideoBean> data) {
        this.data = data;
    }

    public static class VideoBean implements Parcelable{
        /**
         * FileID : e4faa65fc867ebc8
         * FileName : Teacher(23-31-04).flv
         * StartTime : 2018-04-22%2023%3A31%3A04
         * EndTime : 2018-04-23%2000%3A31%3A04
         * FileSize : 1563760732
         * FtpUrl : ftp%3A%2F%2F192.168.2.81%3A8225%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F22%E6%97%A5%2F23%E6%97%B631%E5%88%8604%E7%A7%92%2FTeacher(23-31-04).flv%24LangeFtpUser%24LangeFtpPwd*_!
         * HttpUrl : http%3A%2F%2F192.168.2.81%3A8020%2FLgFtp%2FH1%2F1%2F%E7%9B%91%E6%8E%A7%E8%B5%84%E6%BA%90%2F311%2F2018%E5%B9%B404%E6%9C%88%2F22%E6%97%A5%2F23%E6%97%B631%E5%88%8604%E7%A7%92%2FTeacher(23-31-04).flv
         */

        private String FileID;
        private String FileName;
        private String StartTime;
        private String EndTime;
        private long FileSize;
        private String FtpUrl;
        private String HttpUrl;
        private String ClassHourNO;
        private String ClassHourName;

        public VideoBean(){

        }

        public String getFileID() {
            return FileID;
        }

        public void setFileID(String FileID) {
            this.FileID = FileID;
        }

        public String getFileName() {
            return FileName;
        }

        public void setFileName(String FileName) {
            this.FileName = FileName;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public long getFileSize() {
            return FileSize;
        }

        public void setFileSize(long FileSize) {
            this.FileSize = FileSize;
        }

        public String getFtpUrl() {
            return FtpUrl;
        }

        public void setFtpUrl(String FtpUrl) {
            this.FtpUrl = FtpUrl;
        }

        public String getHttpUrl() {
            return HttpUrl;
        }

        public void setHttpUrl(String HttpUrl) {
            this.HttpUrl = HttpUrl;
        }

        public String getClassHourNO() {
            return ClassHourNO == null ? "" : ClassHourNO;
        }

        public void setClassHourNO(String classHourNO) {
            ClassHourNO = classHourNO;
        }

        public String getClassHourName() {
            return ClassHourName == null ? "" : ClassHourName;
        }

        public void setClassHourName(String classHourName) {
            ClassHourName = classHourName;
        }

        public static final Parcelable.Creator<VideoBean> CREATOR = new Creator<VideoBean>() {

            @Override
            public VideoBean[] newArray(int size) {
                return new VideoBean[size];
            }

            @Override
            public VideoBean createFromParcel(Parcel source) {
                return new VideoBean(source);
            }
        };

        public VideoBean(Parcel source) {
            FileID = source.readString();
            FileName = source.readString();
            StartTime = source.readString();
            EndTime = source.readString();
            FileSize = source.readLong();
            FtpUrl = source.readString();
            HttpUrl = source.readString();
            ClassHourNO = source.readString();
            ClassHourName = source.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(FileID);
            dest.writeString(FileName);
            dest.writeString(StartTime);
            dest.writeString(EndTime);
            dest.writeLong(FileSize);
            dest.writeString(FtpUrl);
            dest.writeString(HttpUrl);
            dest.writeString(ClassHourNO);
            dest.writeString(ClassHourName);
        }

        @Override
        public String toString() {
            return "VideoBean{" +
                    "FileID='" + FileID + '\'' +
                    ", FileName='" + FileName + '\'' +
                    ", StartTime='" + StartTime + '\'' +
                    ", EndTime='" + EndTime + '\'' +
                    ", FileSize=" + FileSize +
                    ", FtpUrl='" + FtpUrl + '\'' +
                    ", HttpUrl='" + HttpUrl + '\'' +
                    ", ClassHourNO='" + ClassHourNO + '\'' +
                    ", ClassHourName='" + ClassHourName + '\'' +
                    '}';
        }
    }
}
