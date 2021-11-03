using System;
using System.Collections.Generic;
using System.Diagnostics;
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
    /// Логика взаимодействия для Results.xaml
    /// </summary>
    public partial class Results : Page
    {
        Applications applications;
        GemoTestEntities db = new GemoTestEntities();
        List<AnalyzesResults> results;
        AnalyzesResults result;

        public Results(Applications applications)
        {
            InitializeComponent();

            this.applications = applications;

            if (applications.order != null)
            {
                txtName.Text = db.Study.Find(applications.order.id_Study).Name + " - РЕЗУЛЬТАТ";
                txtDate.Text = "Дата проведения исследования: " + applications.order.Date.Value.ToShortDateString();
                txtDoctor.Text = "Исследование провел: " + db.Doctors.Find(applications.order.id_Doctor).Name;
                txtNumber.Text = "Номер документа заказа: " + applications.order.Code;

                Update();
            }
        }

        private void gridResults_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //result = gridResults.SelectedValue as AnalyzesResults;
            //AddResults add = new AddResults(result, db);
            //add.ShowDialog();
            //Update();
        }

        private void Update()
        {
            gridResults.ItemsSource = null;
            gridResults.ItemsSource = db.AnalyzesResults.Where(x => x.id_Patient == applications.order.id_Patient).Distinct().ToList();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            db.SaveChanges();
        }
    }
}
