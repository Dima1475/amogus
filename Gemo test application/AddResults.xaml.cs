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
using System.Windows.Shapes;

namespace Gemo_test_application
{
    /// <summary>
    /// Логика взаимодействия для AddResults.xaml
    /// </summary>
    public partial class AddResults : Window
    {
        AnalyzesResults result;
        GemoTestEntities db;

        public AddResults(AnalyzesResults result, GemoTestEntities db)
        {
            InitializeComponent();

            this.result = result;
            this.db = db;
        }

        private void txtResult_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            if (!Char.IsDigit(e.Text, 0) && (e.Text != ",")) e.Handled = true;
        }

        private void btnAddResult_Click(object sender, RoutedEventArgs e)
        {
            result.Result = Convert.ToDecimal(txtResult.Text);
            db.SaveChanges();
            Hide();
        }
    }
}
