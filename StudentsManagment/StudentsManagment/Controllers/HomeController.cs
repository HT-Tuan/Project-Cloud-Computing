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
    string baseUrl = "http://host.docker.internal:8090/";
    //string baseUrl = "https://jsonplaceholder.typicode.com/";



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

    public IActionResult LopHocPhan()
    {
        return View();
    }
    public IActionResult Monhoc()
    {
        return View();
    }
    public IActionResult ThamGiaHoc()
    {
        return View();
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

