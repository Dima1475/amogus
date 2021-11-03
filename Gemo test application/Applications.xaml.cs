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
using System.Windows.Shapes;

namespace Gemo_test_application
{
    /// <summary>
    /// Логика взаимодействия для Applications.xaml
    /// </summary>
    public partial class Applications : Window
    {
        public Study study;
        public Orders order;

        Register register;
        Results results;
        GemoTestEntities db = new GemoTestEntities();
        int idWindow = 0;

        public Applications(Study study)
        {
            InitializeComponent();

            this.study = study;

            register = new Register(this);

            idWindow = 0;

            mainFrame.Navigate(register);
        }



        private void btnClose_Click(object sender, RoutedEventArgs e)
        {
            Hide();
        }

        private void btnAccept_Click(object sender, RoutedEventArgs e)
        {
            if (register.datepickerDate.Text.Length > 0 && register.txtAnalyzesPrice.Text.Length > 0 && register.txtDocumentNumber.Text.Length > 0 && register.txtEndPrice.Text.Length > 0 && register.txtServicesPrice.Text.Length > 0 && register.txtStudyName.Text.Length > 0 && register.comboboxDoctor.Text.Length > 0 && register.comboboxPacient.Text.Length > 0 && idWindow == 0)
            {
                order = new Orders();
                order.Date = Convert.ToDateTime(register.datepickerDate.SelectedDate.Value.ToShortDateString());
                order.EndPrice = register.analyzesPrice + Convert.ToDecimal(register.servicesPrice);
                order.Code = register.txtDocumentNumber.Text;
                order.id_Doctor = db.Doctors.Where(x => x.Name == register.comboboxDoctor.Text).FirstOrDefault().id;
                order.id_Patient = Convert.ToInt32(register.comboboxPacient.Text.Split()[3]);
                order.id_Study = study.id;
                for (int i = 0; i < register.analyzesIds.Count; i++)
                {
                    AnalyzesResults analyzesResults = new AnalyzesResults();
                    analyzesResults.id_Patient = order.id_Patient;
                    analyzesResults.Date = order.Date;
                    analyzesResults.id_Analyze = register.analyzesIds[i];
                    db.AnalyzesResults.Add(analyzesResults);
                }
                db.Orders.Add(order);
                db.SaveChanges();
                MessageBox.Show("Заявка успешно создана!");
                idWindow = 1;
                results = new Results(this);
                mainFrame.Navigate(results);
            }
            else if (idWindow == 1)
            {
                
            }
            else
                MessageBox.Show("Заполните все поля!");
        }
    }
}
