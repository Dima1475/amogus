using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Gemo_test_application
{
    /// <summary>
    /// Логика взаимодействия для Register.xaml
    /// </summary>
    public partial class Register : Page
    {
        GemoTestEntities db = new GemoTestEntities();
        Applications applications;

        public double servicesPrice = 0;
        public decimal? analyzesPrice = 0;
        public List<int?> analyzesIds = new List<int?>();

        public Register(Applications applications)
        {
            InitializeComponent();

            this.applications = applications;

            txtStudyName.Text = applications.study.Name;

            gridServices.ItemsSource = db.ServicesForStudy.Where(x => x.id_Study == applications.study.id).ToList();
            gridAnalyzes.ItemsSource = db.AnalyzesForStudy.Where(x => x.id_Study == applications.study.id).ToList().Select((x, i) => { x.position = i; return x; }).ToList();

            comboboxDoctor.ItemsSource = db.Doctors.ToList().Select(x => x.Name);
            comboboxPacient.ItemsSource = db.Patients.ToList().Select(x => x.Name + " " + x.id);

            db.Services.ToList().ForEach(x => servicesPrice += Convert.ToDouble(x.Price));
            txtServicesPrice.Text = $"Итого стоимость услуг: {servicesPrice}р";

            analyzesIds = db.AnalyzesForStudy.Where(x => x.id_Study == applications.study.id).Select(x => x.id_Analyzes).ToList();
            for (int i = 0; i < analyzesIds.Count; i++)
            {
                db.ElementsNeedForAnalyze.ToList().Where(x => x.id_Analyze == analyzesIds[i]).ToList().ForEach(x => analyzesPrice += x.Elements.Price * x.Count * 2);
            }
            txtAnalyzesPrice.Text = $"Итого стоимость анализов: {analyzesPrice}";

            txtEndPrice.Text = $"ИТОГ: {Convert.ToDouble(analyzesPrice) + servicesPrice}р";
        }
    }
}
