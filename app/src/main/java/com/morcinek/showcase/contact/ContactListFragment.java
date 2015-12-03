package com.morcinek.showcase.contact;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.morcinek.showcase.R;
import com.morcinek.showcase.contact.model.Contact;
import com.morcinek.showcase.general.AbstractListFragment;
import com.morcinek.showcase.general.adapter.AbstractRecyclerViewAdapter;
import com.morcinek.showcase.general.network.requesters.ContactsRequester;

import javax.inject.Inject;

import lombok.Getter;

public class ContactListFragment extends AbstractListFragment<Contact> {

    @Getter
    @Inject
    ContactsRequester networkRequester;

    @Inject
    ClipboardManager clipboardManager;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.contact_list;
    }

    @Override
    protected AbstractRecyclerViewAdapter<Contact, ? extends RecyclerView.ViewHolder> getCreateListAdapter() {
        return new ContactListAdapter(getActivity());
    }

    @Override
    public String getTitle() {
        return getString(R.string.contact_title);
    }

    @Override
    public void onRefresh() {
        networkRequester.requestContacts();
    }

    @Override
    public void onItemClicked(Contact contact) {
        switch (contact.getType()) {
            case Phone:
                invokePhone(contact);
                break;
            case Email:
                invokeEmail(contact);
                break;
            case Skype:
                invokeSkype(contact);
                break;
            case Line:
                invokeLine(contact);
                break;
            case Play:
                invokePlay(contact);
                break;
            case Website:
            case Github:
            case Linkedin:
                invokeWebsite(contact);
                break;
        }
    }

    private void invokePhone(Contact contact) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contact.getContent())));
    }

    private void invokeEmail(Contact contact) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + contact.getContent()));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.action_mail_subject));
        startActivity(emailIntent);
    }

    private void invokeSkype(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setClassName("com.skype.raider", "com.skype.raider.Main");
        intent.setData(Uri.parse(String.format("skype:%s?chat", contact.getContent())));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void invokeLine(Contact contact) {
        clipboardManager.setPrimaryClip(ClipData.newPlainText("LineId", contact.getContent()));
        Toast.makeText(getActivity(), R.string.action_line_message, Toast.LENGTH_LONG).show();
    }

    private void invokeWebsite(Contact contact) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(contact.getContent())));
    }

    private void invokePlay(Contact contact) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Tomasz Morcinek")));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(contact.getContent())));
        }
    }

    private boolean isApplicationInstalled(String appPackage) {
        PackageManager packageManager = getActivity().getPackageManager();
        try {
            packageManager.getPackageInfo(appPackage, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return true;
    }
}
