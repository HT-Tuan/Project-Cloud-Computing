using System.Diagnostics;
using System.Net.Http.Headers;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using StudentsManagment.Models;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;

namespace StudentsManagment.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;
    string baseUrl = "http://JavaAPI:8090/";


    public HomeController(ILogger<HomeController> logger)
    {
        _logger = logger;
    }

    public IActionResult Index()
    {
        return View();
    }

    public IActionResult Privacy()
    {
        return View();
    }

    public async Task<ActionResult> Students()
    {
        List<SinhVienModel> SVInfo = new List<SinhVienModel>();
        using (var client = new HttpClient())
        {
            //Passing service base url
            client.BaseAddress = new Uri(baseUrl);
            client.DefaultRequestHeaders.Clear();
            //Define request data format
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //Sending request to find web api REST service resource GetAllEmployees using HttpClient
            HttpResponseMessage Res = await client.GetAsync("sinhviens");

            //Checking the response is successful or not which is sent using HttpClient
            if (Res.IsSuccessStatusCode)
            {
                //Storing the response details recieved from web api
                var SVResponse = Res.Content.ReadAsStringAsync().Result;

                //Deserializing the response recieved from web api and storing into the Employee list
                SVInfo = JsonConvert.DeserializeObject<List<SinhVienModel>>(SVResponse);
            }
            //returning the employee list to view

            return View(SVInfo);
        }
    }
    public IActionResult AddStudentForm()
    {
        return View();
    }

[HttpPost]
    //string mssv, string hoDem, string ten,int namHoc, int namNhapHoc, string gioiTinh, string ctdt
    //public ActionResult AddStudentForm(SinhVienModel student)
    public ActionResult AddStudentForm(string mssv, string hoDem, string ten, int namHoc, int namNhapHoc, DateTime ngaysinh, string gioiTinh, string ctdt)
    {
        SinhVienModel student = new SinhVienModel();
        student.masinhvien = mssv;
        student.hodem = hoDem;
        student.ten = ten;
        student.namhoc = namHoc;
        student.namnhaphoc = namNhapHoc;
        student.ngaysinh = ngaysinh;
        student.gioitinh = gioiTinh == "Nam" ? true : false;
        student.tenChuongTrinhDaoTao = ctdt;

        using (var client = new HttpClient())
        {
            client.BaseAddress = new Uri(baseUrl + "sinhvien");

            //HTTP POST
            var postTask = client.PostAsJsonAsync<SinhVienModel>("sinhvien", student);
            postTask.Wait();

            var result = postTask.Result;
            if (result.IsSuccessStatusCode)
            {
                return RedirectToAction("Students");
            }
        }

        ModelState.AddModelError(string.Empty, "Server Error. Please contact administrator.");

        return View(student);
    }

    public IActionResult UpdateStudentForm()
    {
        return View();
    }

    public async Task<ActionResult> ChuongTrinhDaotao()
    {
        List<ChuongTrinhDaoTaoModel> CtdtInfo = new List<ChuongTrinhDaoTaoModel>();
        using (var client = new HttpClient())
        {
            //Passing service base url
            client.BaseAddress = new Uri(baseUrl);
            client.DefaultRequestHeaders.Clear();
            //Define request data format
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //Sending request to find web api REST service resource GetAllEmployees using HttpClient
            HttpResponseMessage Res = await client.GetAsync("chuongtrinhs");

            //Checking the response is successful or not which is sent using HttpClient
            if (Res.IsSuccessStatusCode)
            {
                //Storing the response details recieved from web api
                var ctdtResponse = Res.Content.ReadAsStringAsync().Result;

                //Deserializing the response recieved from web api and storing into the Employee list
                CtdtInfo = JsonConvert.DeserializeObject<List<ChuongTrinhDaoTaoModel>>(ctdtResponse);
            }
            //returning the employee list to view
   
            return View(CtdtInfo);
        }
    }

    public async Task<ActionResult> LopHocPhan()
    {
        List<LopHocPhanModel> LHPInfo = new List<LopHocPhanModel>();
        using (var client = new HttpClient())
        {
            //Passing service base url
            client.BaseAddress = new Uri(baseUrl);
            client.DefaultRequestHeaders.Clear();
            //Define request data format
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //Sending request to find web api REST service resource GetAllEmployees using HttpClient
            HttpResponseMessage Res = await client.GetAsync("api/lophocphan");

            //Checking the response is successful or not which is sent using HttpClient
            if (Res.IsSuccessStatusCode)
            {
                //Storing the response details recieved from web api
                var LHPResponse = Res.Content.ReadAsStringAsync().Result;

                //Deserializing the response recieved from web api and storing into the Employee list
                LHPInfo = JsonConvert.DeserializeObject<List<LopHocPhanModel>>(LHPResponse);
            }
            //returning the employee list to view

            return View(LHPInfo);
        }
    }

    public async Task<ActionResult> MonHoc()
    {
        List<MonHocModel> MonHocInfo = new List<MonHocModel>();
        using (var client = new HttpClient())
        {
            //Passing service base url
            client.BaseAddress = new Uri(baseUrl);
            client.DefaultRequestHeaders.Clear();
            //Define request data format
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //Sending request to find web api REST service resource GetAllEmployees using HttpClient
            HttpResponseMessage Res = await client.GetAsync("api/monhoc");

            //Checking the response is successful or not which is sent using HttpClient
            if (Res.IsSuccessStatusCode)
            {
                //Storing the response details recieved from web api
                var MonHocResponse = Res.Content.ReadAsStringAsync().Result;

                //Deserializing the response recieved from web api and storing into the Employee list
                MonHocInfo = JsonConvert.DeserializeObject<List<MonHocModel>>(MonHocResponse);
            }
            //returning the employee list to view

            return View(MonHocInfo);
        }

    }
    public async Task<ActionResult> ThamGiaHoc()
    {
        List<ThamGiaHocModel> ThamGiaHocInfo = new List<ThamGiaHocModel>();
        using (var client = new HttpClient())
        {
            //Passing service base url
            client.BaseAddress = new Uri(baseUrl);
            client.DefaultRequestHeaders.Clear();
            //Define request data format
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //Sending request to find web api REST service resource GetAllEmployees using HttpClient
            HttpResponseMessage Res = await client.GetAsync("api/thamgiahoc");

            //Checking the response is successful or not which is sent using HttpClient
            if (Res.IsSuccessStatusCode)
            {
                //Storing the response details recieved from web api
                var ThamGiaHocResponse = Res.Content.ReadAsStringAsync().Result;

                //Deserializing the response recieved from web api and storing into the Employee list
                ThamGiaHocInfo = JsonConvert.DeserializeObject<List<ThamGiaHocModel>>(ThamGiaHocResponse);
            }
            //returning the employee list to view

            return View(ThamGiaHocInfo);
        }
    }

    public IActionResult Thongke()
    {
        return View();
    }



    



    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}

