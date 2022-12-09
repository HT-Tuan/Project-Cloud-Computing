using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using StudentsManagment.Models;

namespace StudentsManagment.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;

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

    public IActionResult Students()
    {
        return View();
    }
    public IActionResult AddStudentForm()
    {
        return View();
    }
    public IActionResult UpdateStudentForm()
    {
        return View();
    }
    public IActionResult ChuongTrinhDaotao()
    {
        return View();
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

