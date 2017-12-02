package com.androidprojects.sunilsharma.mysqldemo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by sunil sharma on 12/2/2017.
 */

public class BackgroundWorker extends AsyncTask<String , Void , String>
{

    Context context;

    AlertDialog alertDialog;


    public BackgroundWorker(Context context)
    {
        this.context = context;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected String doInBackground(String... params)
    {
        String type = params[0];
        String login_url = "http://192.168.0.106:8081/employee/login.php";
        String registration_url = "http://192.168.0.106:8081/employee/registration.php";

        if(type.equals("login"))
        {
            try
            {
                String user_name = params[1];
                String password = params[2];

                URL url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream ,
                        "UTF-8"));

                String post_data = URLEncoder.encode("user_name" , "UTF-8")+"="+
                        URLEncoder.encode(user_name , "UTF-8")+"&"+
                        URLEncoder.encode("password" , "UTF-8")+"="+
                        URLEncoder.encode(password , "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream ,
                                "iso-8859-1"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (type.equals("registration"))
        {
            try
            {
                String name = params[1];
                String surname = params[2];
                String age = params[3];
                String username = params[4];
                String password = params[5];

                URL url = new URL(registration_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter =
                        new BufferedWriter(new OutputStreamWriter(outputStream ,
                                "UTF-8"));


                String post_data =
                        URLEncoder.encode("name" , "UTF-8")+"="+URLEncoder.encode(name , "UTF-8")
                                +"&"+
                        URLEncoder.encode("surname" , "UTF-8")+"="+URLEncoder.encode(surname , "UTF-8")
                                +"&"+
                        URLEncoder.encode("age" , "UTF-8")+"="+URLEncoder.encode(age , "UTF-8")
                                +"&"+
                        URLEncoder.encode("username" , "UTF-8")+"="+URLEncoder.encode(username , "UTF-8")
                                +"&"+
                        URLEncoder.encode("password" , "UTF-8")+"="+URLEncoder.encode(password , "UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream ,
                                "iso-8859-1"));


                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result)
    {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
