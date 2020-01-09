package ru.webapp.basejava.storage;

import ru.webapp.basejava.exception.ExistStorageException;
import ru.webapp.basejava.exception.NotExistStorageException;
import ru.webapp.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }


    protected abstract List<Resume> doCopyAll();

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

}
